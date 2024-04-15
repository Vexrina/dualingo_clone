package com.example.dualingo_clone.game_excersise.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dualingo_clone.common.EventHandler
import com.example.dualingo_clone.database.data.DatabaseImpl
import com.example.dualingo_clone.dataclasses.UserInfo
import com.example.dualingo_clone.dataclasses.Word
import com.example.dualingo_clone.word_excersise.data.WordExcersiseRepoImpl
import com.example.dualingo_clone.word_excersise.models.WordExcersiseEvent
import com.example.dualingo_clone.word_excersise.models.WordExcersiseSubState
import com.example.dualingo_clone.word_excersise.models.WordExcersiseViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GameExcersiseViewModel @Inject constructor(
    private val db: DatabaseImpl,
) : ViewModel(), EventHandler<WordExcersiseEvent> {
    private val probabilityOfCorrectAnswer = 20

    private val repo = WordExcersiseRepoImpl(db)

    private val _viewState: MutableStateFlow<WordExcersiseViewState> =
        MutableStateFlow(WordExcersiseViewState())
    val viewState: StateFlow<WordExcersiseViewState> = _viewState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val (user, userInfo) = repo.getUserData()
            _viewState.value = _viewState.value.copy(
                user = user,
                userInfo = userInfo,
            )
        }
        nextQuest()
    }

    override fun obtainEvent(event: WordExcersiseEvent) {
        when (event) {
            is WordExcersiseEvent.AnswerChanged -> answerChanged(event.value)
            WordExcersiseEvent.CheckAnswer -> checkAnswer()
            WordExcersiseEvent.None -> nextQuest()
            WordExcersiseEvent.TryAgain -> tryAgain()
            WordExcersiseEvent.NextQuest -> nextQuest()
        }
    }

    private fun answerChanged(value: Word) {
        _viewState.value = _viewState.value.copy(
            selectedWord = value
        )
    }

    private fun nextQuest() {
        viewModelScope.launch(Dispatchers.IO) {
            _viewState.value = _viewState.value.copy(
                isLoading = true,
                wordSubState = WordExcersiseSubState.Quest,
            )
            val quest = repo.getRandomExcersise()
            val answer = quest[Random.nextInt(4)]
            _viewState.value = _viewState.value.copy(
                questValue = quest,
                correctWord = answer,
            )
            waitSecondPlayer(this)
        }
    }
    private fun waitSecondPlayer(coroutineScope: CoroutineScope) {
        coroutineScope.launch{
            for (i in 0..100) {
                _viewState.value = _viewState.value.copy(
                    progress =  i / 100f
                )
                delay(1) // TODO: Create logic for find second player
            }
            _viewState.value = _viewState.value.copy(
                isLoading = false,
                progress = 1f
            )
        }
    }
    private fun checkAnswer() {
        robotAnswerChoose()
        _viewState.value = _viewState.value.copy(
            wordSubState = WordExcersiseSubState.Correct
        )
    }

    private fun updatePoints() {
        val correctWord = _viewState.value.correctWord
        val selectedWord = _viewState.value.selectedWord

        if (correctWord == selectedWord) {
            val streak = _viewState.value.streak + 1
            val userInfo = _viewState.value.userInfo!!
            var points = userInfo.points

            if (streak >= 2) {
                points += 1 + 0.2 * streak
            } else {
                points += 1
            }

            val newInfo = UserInfo(
                userId = userInfo.userId,
                languageId = userInfo.languageId,
                imageURL = userInfo.imageURL,
                points = points,
            )

            _viewState.value = _viewState.value.copy(
                streak = streak,
                userInfo = newInfo
            )

            viewModelScope.launch(Dispatchers.IO) {
                repo.updatePoints(newInfo)
            }

        } else {
            _viewState.value = _viewState.value.copy(
                streak = 0
            )
        }
    }

    private fun tryAgain() {
        val currentState = _viewState.value
        _viewState.value = currentState.copy(wordSubState = WordExcersiseSubState.Quest)
    }

    private fun robotAnswerChoose() {
        val elements = _viewState.value.questValue
        val randomValue = Random.nextDouble()

        val selectedOption: Word = if (randomValue <= probabilityOfCorrectAnswer / 100.0) {
            _viewState.value.correctWord
        } else {
            val incorrectIndex = (elements.indices)
                .filterNot { it == elements.indexOf(_viewState.value.correctWord) }.random()
            elements[incorrectIndex]
        }

        _viewState.value = _viewState.value.copy(
            robotSelectWord = selectedOption
        )
    }

}