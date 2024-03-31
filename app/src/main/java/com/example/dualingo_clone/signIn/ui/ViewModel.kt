package com.example.dualingo_clone.signIn.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dualingo_clone.common.EventHandler
import com.example.dualingo_clone.database.data.DatabaseImpl
import com.example.dualingo_clone.dataclasses.LoginEventMessageTuple
import com.example.dualingo_clone.dataclasses.User
import com.example.dualingo_clone.signIn.data.LoginRepositoryImpl
import com.example.dualingo_clone.signIn.models.LoginEvent
import com.example.dualingo_clone.signIn.models.LoginSubState
import com.example.dualingo_clone.signIn.models.LoginViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private var db: DatabaseImpl
) : ViewModel(), EventHandler<LoginEvent> {

    private val repo = LoginRepositoryImpl(db)

    private val _viewState: MutableStateFlow<LoginViewState> = MutableStateFlow(LoginViewState())
    val viewState: StateFlow<LoginViewState> = _viewState

    override fun obtainEvent(event: LoginEvent) {
        when (event) {
            LoginEvent.ActionClicked -> switchActionState()
            LoginEvent.LoginActionInvoked -> loginActionInvoked()
            LoginEvent.SignUpBack -> signUpBackState()
            LoginEvent.ErrorShown -> sendErrorEvent(null)
            is LoginEvent.EmailChanged -> emailChanged(event.value)
            is LoginEvent.PasswordChanged -> passwordChanged(event.value)
            is LoginEvent.ConfirmPasswordChanged -> confirmPasswordChanged(event.value)
            is LoginEvent.ForgetClicked -> forgetClicked()
            is LoginEvent.LoginClicked -> loginClicked()
            is LoginEvent.RegisterClicked -> registerClicked()
            is LoginEvent.LastNameChanged -> lastNameChanged(event.value)
            is LoginEvent.FirstNameChanged -> firstNameChanged(event.value)
            else -> {}
        }
    }

    private fun switchActionState() {
        val currentState = _viewState.value
        val newState = when (currentState.loginSubState) {
            LoginSubState.SignIn -> currentState.copy(loginSubState = LoginSubState.SignUpStart)
            LoginSubState.SignUpStart -> currentState.copy(loginSubState = LoginSubState.SignIn)
            LoginSubState.SignUpEnd -> currentState.copy(loginSubState = LoginSubState.SignIn)
            LoginSubState.Forgot -> currentState.copy(loginSubState = LoginSubState.SignIn)
        }
        _viewState.value = newState
    }

    private fun signUpBackState() {
        val currentState = _viewState.value
        val newState = when (currentState.loginSubState) {
            LoginSubState.SignUpEnd -> currentState.copy(loginSubState = LoginSubState.SignUpStart)
            else -> currentState
        }
        _viewState.value = newState
    }

    private fun firstNameChanged(value: String) {
        val currentState = _viewState.value
        val newState = currentState.copy(
            firstNameValue = value.filter {
                it.isLetter()
            }
        )
        _viewState.value = newState
    }

    private fun lastNameChanged(value: String) {
        val currentState = _viewState.value
        val newState = currentState.copy(
            lastNameValue = value.filter {
                it.isLetter()
            }
        )
        _viewState.value = newState
    }

    private fun emailChanged(value: String) {
        val currentState = _viewState.value
        val newState = currentState.copy(emailValue = value)
        _viewState.value = newState
    }

    private fun passwordChanged(value: String) {
        val currentState = _viewState.value
        val newState = currentState.copy(passwordValue = value)
        _viewState.value = newState
    }

    private fun confirmPasswordChanged(value: String) {
        val currentState = _viewState.value
        val newState = currentState.copy(confirmPasswordValue = value)
        _viewState.value = newState
    }

    private fun forgetClicked() {
        val currentState = _viewState.value
        val newState = currentState.copy(loginSubState = LoginSubState.Forgot)
        _viewState.value = newState
    }

    private fun sendErrorEvent(errorMessage: String?) {
        if (errorMessage != null) {
            viewModelScope.launch {
                _viewState.value.eventChannel.send(
                    LoginEventMessageTuple(
                        Event = LoginEvent.ErrorShown,
                        Message = errorMessage,
                    )
                )
            }
        }
    }

    private fun sendSuccessEvent() {
        viewModelScope.launch {
            _viewState.value.eventChannel.send(
                LoginEventMessageTuple(
                    Event = LoginEvent.ActionClicked,
                    Message = "",
                )
            )
        }
    }

    private fun registerClicked() {
        val currentState = _viewState.value
        val newState = if (currentState.loginSubState == LoginSubState.SignUpStart) {
            LoginSubState.SignUpEnd
        } else {
            performRegister()
            currentState.loginSubState
        }
        val updatedState = currentState.copy(loginSubState = newState)

        _viewState.value = updatedState
    }

    private fun performRegister() {
        viewModelScope.launch {
            if (
                _viewState.value.firstNameValue.isEmpty() ||
                _viewState.value.lastNameValue.isEmpty() ||
                _viewState.value.emailValue.isEmpty() ||
                _viewState.value.passwordValue.isEmpty() ||
                _viewState.value.confirmPasswordValue.isEmpty()
            ) {
                sendErrorEvent("Some fields is empty")
                return@launch
            }
            val user = User(
                firstName = _viewState.value.firstNameValue,
                lastName = _viewState.value.lastNameValue,
                password = _viewState.value.passwordValue,
                email = _viewState.value.emailValue,
            )

            if (!isValidEmail(user.email)) {
                sendErrorEvent("Email is not valid")
                return@launch
            } else if (_viewState.value.passwordValue != _viewState.value.confirmPasswordValue) {
                sendErrorEvent("Passwords don't match")
                return@launch
            } else {
                val (ok, err) = repo.signUp(user)
                if (err != null || !ok) {
                    sendErrorEvent(err ?: "Unknown error")
                    return@launch
                }
                repo.signInToCache(user.email, user.password)
                sendSuccessEvent()
            }
        }
    }

    private fun loginActionInvoked() {}

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[a-z0-9]+@[a-z0-9]+\\.[a-z]{2,}\$")
        return emailRegex.matches(email)
    }


    private fun loginClicked() {
        viewModelScope.launch {
            val email = _viewState.value.emailValue
            val password = _viewState.value.passwordValue
            if (email.isEmpty() || password.isEmpty()) {
                sendErrorEvent("Some field is empty")
                return@launch
            }
            val (user, err) = repo.signIn(email, password)
            if (err!=null){
                sendErrorEvent(err)
                return@launch
            }
            if (user.email==email && user.password==password){
                repo.signInToCache(email, password)
                sendSuccessEvent()
                return@launch
            } else {
                sendErrorEvent("BadRequest")
                return@launch
            }
        }
    }
}