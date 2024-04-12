package com.example.dualingo_clone.animal_excersise.models

import android.graphics.Bitmap
import com.example.dualingo_clone.dataclasses.Quest
import com.example.dualingo_clone.dataclasses.User
import com.example.dualingo_clone.dataclasses.UserInfo

enum class AnimalExcersiseSubState {
    Quest, Correct, Uncorrect
}

sealed class ProfileAction{
    data class OpenDashBoard(val username: String) : ProfileAction()
    object None : ProfileAction()
}

data class AnimalExcersiseViewState(
    val animalSubState: AnimalExcersiseSubState = AnimalExcersiseSubState.Quest,
    val answerValue: String = "",
    val questValue: Quest = Quest(),
    val userInfo: UserInfo? = null,
    val user: User = User(),
    val isLoading: Boolean = true,
    val isProgress: Boolean = false,
    val streak: Int = 0,
)