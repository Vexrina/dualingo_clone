package com.example.dualingo_clone.animal_excersise.ui

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dualingo_clone.animal_excersise.data.AnimalExcersiseRepoImpl
import com.example.dualingo_clone.animal_excersise.models.AnimalExcersiseEvent
import com.example.dualingo_clone.animal_excersise.models.AnimalExcersiseSubState
import com.example.dualingo_clone.animal_excersise.models.AnimalExcersiseViewState
import com.example.dualingo_clone.common.EventHandler
import com.example.dualingo_clone.database.data.DatabaseImpl
import com.example.dualingo_clone.dataclasses.Quest
import com.example.dualingo_clone.dataclasses.UserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class AnimalExcersiseViewModel @Inject constructor(
    private val db: DatabaseImpl
):ViewModel(), EventHandler<AnimalExcersiseEvent> {
    private val repo = AnimalExcersiseRepoImpl(db)

    private val _viewState: MutableStateFlow<AnimalExcersiseViewState> = MutableStateFlow(AnimalExcersiseViewState())
    val viewState: StateFlow<AnimalExcersiseViewState> = _viewState
    init {
        viewModelScope.launch(Dispatchers.IO) {
            _viewState.value = _viewState.value.copy(
                isLoading = true,
            )
            delay(500)
            val (user, userInfo) = repo.getUserData()
            val quest = repo.getRandomExcersise(user)
            _viewState.value = _viewState.value.copy(
                questValue = quest.copy(imageBitmap = urlToBitmap(quest.imgURL)),
                user = user,
                userInfo = userInfo,
                isLoading = false,
            )
        }
    }

    override fun obtainEvent(event: AnimalExcersiseEvent) {
        when (event){
            is AnimalExcersiseEvent.AnswerChanged -> answerChanged(event.value)
            AnimalExcersiseEvent.CheckAnswer -> checkAnswer()
            AnimalExcersiseEvent.None -> nextQuest()
            AnimalExcersiseEvent.TryAgain -> tryAgain()
            AnimalExcersiseEvent.NextQuest -> nextQuest()
        }
    }

    private fun nextQuest(){
        viewModelScope.launch(Dispatchers.IO) {
            _viewState.value = _viewState.value.copy(
                isLoading = true,
                animalSubState = AnimalExcersiseSubState.Quest,
                answerValue = "",
            )
            val user = _viewState.value.user
            val quest = repo.getRandomExcersise(user)
            _viewState.value = _viewState.value.copy(
                questValue = quest.copy(imageBitmap = urlToBitmap(quest.imgURL)),
                isLoading = false,
            )
        }
    }

    private fun checkAnswer(){
        val userAnswer = _viewState.value.answerValue
        val correct = _viewState.value.questValue.answer1

        val currentState = _viewState.value
        val streak = _viewState.value.streak

        if (correct.equals(userAnswer, ignoreCase=true)){
            val userPoints = _viewState.value.userInfo!!.points
            var newPoints = userPoints+2
            Log.d("userinfo", "${_viewState.value.userInfo!!.points}")
            if (streak+1>=2){
                newPoints+= 0.2*(streak+1)
            }

            val newInfo = UserInfo(
                userId = _viewState.value.userInfo!!.userId,
                points = newPoints,
                languageId =_viewState.value.userInfo!!.languageId,
                imageURL = _viewState.value.userInfo!!.imageURL,
            )

            _viewState.value = currentState.copy(
                animalSubState = AnimalExcersiseSubState.Correct,
                streak = streak+1,
                userInfo = newInfo,
            )
            Log.d("userinfo", "${newInfo.points}")
            setCompletedQuest(newInfo, _viewState.value.questValue)
        } else{
            _viewState.value = currentState.copy(
                animalSubState = AnimalExcersiseSubState.Uncorrect,
                streak = 0
            )
        }
    }

    private fun urlToBitmap(imgUrl: String):ImageBitmap{
        val byteArray = URL(imgUrl).readBytes()
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        return bitmap.asImageBitmap()
    }

    private fun answerChanged(value: String){
        _viewState.value = _viewState.value.copy(answerValue = value)
    }

    private fun tryAgain(){
        val currentState = _viewState.value
        _viewState.value = currentState.copy(animalSubState = AnimalExcersiseSubState.Quest)
    }

    private fun setCompletedQuest(userInfo: UserInfo, quest: Quest){
        viewModelScope.launch(Dispatchers.IO) {
            repo.setCompleteExcersise(userInfo, quest)
        }
    }
}