package com.example.dualingo_clone.profile.models

import android.graphics.Bitmap
import com.example.dualingo_clone.dataclasses.User

enum class ProfileSubState {
    Profile, Photo
}

sealed class ProfileAction{
    data class OpenDashBoard(val username: String) : ProfileAction()
    object None : ProfileAction()
}

data class ProfileViewState(
    val profileSubState: ProfileSubState = ProfileSubState.Profile,
    val image: Bitmap? = null,
    val user: User? = null,
)