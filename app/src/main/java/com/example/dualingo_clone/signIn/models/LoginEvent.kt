package com.example.dualingo_clone.signIn.models

sealed class LoginEvent {
    object None: LoginEvent()
    object LoginActionInvoked : LoginEvent()
    object ActionClicked : LoginEvent()
    object  SignUpBack : LoginEvent()
    object ErrorShown : LoginEvent()
    object ForgetClicked : LoginEvent()
    object LoginClicked : LoginEvent()
    object RegisterClicked : LoginEvent()
    data class EmailChanged(val value: String) : LoginEvent()
    data class PasswordChanged(val value: String) : LoginEvent()
    data class ConfirmPasswordChanged(val value: String) : LoginEvent()
    data class FirstNameChanged(val value: String) : LoginEvent()
    data class LastNameChanged(val value: String) : LoginEvent()
}