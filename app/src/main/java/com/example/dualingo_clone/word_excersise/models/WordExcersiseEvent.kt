package com.example.dualingo_clone.word_excersise.models

import com.example.dualingo_clone.dataclasses.Word

sealed class WordExcersiseEvent {
    object None : WordExcersiseEvent()
    object CheckAnswer : WordExcersiseEvent()
    object TryAgain : WordExcersiseEvent()
    object NextQuest : WordExcersiseEvent()
    data class AnswerChanged(val value: Word) : WordExcersiseEvent()
}