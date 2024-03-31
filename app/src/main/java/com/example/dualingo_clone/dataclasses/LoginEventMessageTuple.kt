package com.example.dualingo_clone.dataclasses

import com.example.dualingo_clone.signIn.models.LoginEvent

data class LoginEventMessageTuple(
    val Event: LoginEvent,
    val Message: String
)
