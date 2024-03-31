package com.example.dualingo_clone.signIn.models

import com.example.dualingo_clone.dataclasses.LoginEventMessageTuple
import kotlinx.coroutines.channels.Channel
import org.postgresql.util.ServerErrorMessage

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
    val errorMessage: String? = null,
    val eventChannel: Channel<LoginEventMessageTuple> = Channel(Channel.BUFFERED)
)