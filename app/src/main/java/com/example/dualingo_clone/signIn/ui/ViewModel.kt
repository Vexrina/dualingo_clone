package com.example.dualingo_clone.signIn.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.dualingo_clone.common.EventHandler
import com.example.dualingo_clone.signIn.models.LoginEvent
import com.example.dualingo_clone.signIn.models.LoginSubState
import com.example.dualingo_clone.signIn.models.LoginViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel(), EventHandler<LoginEvent> {
    private val _viewState: MutableStateFlow<LoginViewState> = MutableStateFlow(LoginViewState())
    val viewState : StateFlow<LoginViewState> = _viewState
    override fun obtainEvent(event: LoginEvent){
        when (event){
            LoginEvent.ActionClicked -> switchActionState()
            LoginEvent.LoginActionInvoked -> loginActionInvoked()
            is LoginEvent.EmailChanged -> emailChanged(event.value)
            is LoginEvent.PasswordChanged -> passwordChanged(event.value)
            is LoginEvent.ConfirmPasswordChanged -> confirmPasswordChanged(event.value)
            is LoginEvent.ForgetClicked -> forgetClicked()
            is LoginEvent.LoginClicked -> loginClicked()
            is LoginEvent.RegisterClicked -> registerClicked()
            is LoginEvent.LastNameChanged -> lastNameChanged(event.value)
            is LoginEvent.FirstNameChanged -> firstNameChanged(event.value)
        }
    }
    private fun switchActionState() {
        val currentState = _viewState.value
        val newState = when (currentState.loginSubState) {
            LoginSubState.SignIn -> currentState.copy(loginSubState = LoginSubState.SignUpStart)
            LoginSubState.SignUpStart -> currentState.copy(loginSubState = LoginSubState.SignIn)
            else -> currentState
        }
        _viewState.value = newState
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

    private fun performRegister() {}

    private fun loginActionInvoked() {}

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
        Log.d("PASS", "Curr Pass is ${newState.passwordValue}")
        _viewState.value = newState
    }

    private fun confirmPasswordChanged(value: String){
        val currentState = _viewState.value
        val newState = currentState.copy(confirmPasswordValue = value)
        Log.d("CPWD", "Curr confPass is ${newState.confirmPasswordValue}")
        _viewState.value = newState
    }

    private fun forgetClicked() {
        val currentState = _viewState.value
        val newState = currentState.copy(loginSubState = LoginSubState.Forgot)
        _viewState.value = newState
    }


    private fun loginClicked() {
        // TODO: :D
    }
}