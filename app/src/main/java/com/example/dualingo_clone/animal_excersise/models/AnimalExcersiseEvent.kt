package com.example.dualingo_clone.animal_excersise.models

sealed class AnimalExcersiseEvent {
    object None : AnimalExcersiseEvent()
    object CheckAnswer : AnimalExcersiseEvent()
    object TryAgain : AnimalExcersiseEvent()
    object NextQuest : AnimalExcersiseEvent()
    data class AnswerChanged(val value: String) : AnimalExcersiseEvent()
}