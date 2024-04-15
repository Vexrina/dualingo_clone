package com.example.dualingo_clone.word_excersise.models

import com.example.dualingo_clone.dataclasses.User
import com.example.dualingo_clone.dataclasses.UserInfo
import com.example.dualingo_clone.dataclasses.Word

enum class WordExcersiseSubState {
    Quest, Correct
}

sealed class ProfileAction {
    object None : ProfileAction()
}

data class WordExcersiseViewState(
    val wordSubState: WordExcersiseSubState = WordExcersiseSubState.Quest,
    val isLoading: Boolean = true,

    val userInfo: UserInfo? = null,
    val user: User = User(),
    val streak: Int = 0,

    val questValue: List<Word> = emptyList(),
    val correctWord: Word = Word(),
    val enLang: Boolean = true,
    val selectedWord: Word = Word(),

    val robotSelectWord: Word = Word(),
    val progress: Float = 0f,
)