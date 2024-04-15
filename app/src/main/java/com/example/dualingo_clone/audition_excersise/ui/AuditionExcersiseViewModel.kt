package com.example.dualingo_clone.audition_excersise.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dualingo_clone.MyApplication
import com.example.dualingo_clone.audition_excersise.data.AuditionExcersiseRepoImpl
import com.example.dualingo_clone.audition_excersise.voicetotext.VoiceParserState
import com.example.dualingo_clone.audition_excersise.voicetotext.VoiceToTextParser
import com.example.dualingo_clone.database.data.DatabaseImpl
import com.example.dualingo_clone.dataclasses.UserInfo
import com.example.dualingo_clone.dataclasses.Word
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuditionExcersiseViewModel @Inject constructor(
    private val db: DatabaseImpl,
) : ViewModel(){
    private val repo = AuditionExcersiseRepoImpl(db)

    private val user = MutableStateFlow<UserInfo?>(null)
    private val streak = MutableStateFlow(0)

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _word = MutableStateFlow(Word())
    val word: StateFlow<Word> = _word

    private val voiceToTextParser by lazy { VoiceToTextParser(MyApplication.instance) }
    private val _state = voiceToTextParser.state
    val state: StateFlow<VoiceParserState> = _state
    
    init {
        getUser()
        nextQuest()
    }

    private fun getUser(){
        viewModelScope.launch {
            val (_, userinfo) =repo.getUserDate()
            user.value=userinfo
        }
    }

    private fun nextQuest(){
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            _word.value = repo.getRandomExcersise()
            _isLoading.value = false
        }
        voiceToTextParser.clearSpokenText()
    }

    fun next(){
        nextQuest()
        val points = user.value!!.points
        streak.value+=1
        val newPoints = if (streak.value>=2){
            points + 1 + 2 * streak.value
        } else {
            points + 1
        }
        val oldInfo = user.value!!
        val newInfo = UserInfo(
            oldInfo.userId,
            oldInfo.languageId,
            oldInfo.imageURL,
            newPoints,
        )
        user.value = newInfo
        viewModelScope.launch{
            repo.updatePoints(newInfo)
        }
    }

    fun zeroStreak(){
        streak.value=0
    }
    fun startListening(){
        voiceToTextParser.startListening("en")
    }
    fun stopListening(){
        voiceToTextParser.stopListening()
        Log.d("viewmodel", "stoplistening")
    }

}