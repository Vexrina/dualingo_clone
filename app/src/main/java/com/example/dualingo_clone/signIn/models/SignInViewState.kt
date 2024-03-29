package com.example.dualingo_clone.signIn.models

enum class LoginSubState {
    SignIn, SignUpStart, SignUpEnd, Forgot

}

sealed class LoginAction {
    data class OpenDashBoard(val username: String) : LoginAction()
    object None : LoginAction()
}

data class LoginViewState(
    val loginSubState: LoginSubState = LoginSubState.SignIn,
    val emailValue: String = "",
    val passwordValue: String = "",
    val confirmPasswordValue: String = "",
    val firstNameValue: String = "",
    val lastNameValue: String = "",
    val rememberMeChecked: Boolean = false,
    val isProgress: Boolean = false,
    val loginAction: LoginAction = LoginAction.None,
    val phoneNumberValue: String = "",
)