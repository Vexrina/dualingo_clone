package com.example.dualingo_clone.signIn.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dualingo_clone.R
import com.example.dualingo_clone.signIn.models.LoginEvent
import com.example.dualingo_clone.signIn.models.LoginSubState
import com.example.dualingo_clone.signIn.models.LoginViewState
import com.example.dualingo_clone.signIn.view.ForgotView
import com.example.dualingo_clone.signIn.view.SignInView
import com.example.dualingo_clone.signIn.view.SignUpEndView
import com.example.dualingo_clone.signIn.view.SignUpStartView
import com.example.dualingo_clone.ui.components.Header
import com.example.dualingo_clone.ui.components.LabelWithLinkComponent
import kotlinx.coroutines.channels.consumeEach

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    navController: NavController,
) {
    val viewState: LoginViewState by loginViewModel.viewState.collectAsState(initial = LoginViewState())

    val context = LocalContext.current
    with(viewState) {
        Column(
            modifier = Modifier
                .background(Color.White),
        ) {
            LaunchedEffect(Unit){
                val eventChannel = viewState.eventChannel
                eventChannel.consumeEach { event ->
                    when (event.Event){
                        LoginEvent.ErrorShown-> Toast
                            .makeText(
                                context, event.Message, Toast.LENGTH_SHORT
                            )
                            .show()
                        LoginEvent.ActionClicked -> navController.navigate("main")
                        else -> {}
                    }
                }
            }
            Header(
                text = when (loginSubState){
                    LoginSubState.SignIn -> stringResource(id = R.string.sign_in_title)
                    LoginSubState.SignUpStart -> stringResource(id = R.string.sign_up_title)
                    LoginSubState.SignUpEnd -> stringResource(id = R.string.sign_up_title)
                    LoginSubState.Forgot -> stringResource(id = R.string.forgot_title)
                },
                backIcon = true,
                onClick = {
                    when (loginSubState){
                        LoginSubState.SignIn-> { navController.navigate("motherlanguage")}
                        LoginSubState.SignUpStart -> { loginViewModel.obtainEvent(LoginEvent.ActionClicked)}
                        LoginSubState.SignUpEnd -> {loginViewModel.obtainEvent(LoginEvent.SignUpBack)}
                        LoginSubState.Forgot -> { loginViewModel.obtainEvent(LoginEvent.ActionClicked)}
                    }
                },
                modifier = Modifier
                    .width(327.dp)
                ,
            )
            when (loginSubState) {
                    LoginSubState.SignIn ->{
                        SignInView(
                            viewState = this@with,
                            onEmailFieldChange = {
                                loginViewModel.obtainEvent(LoginEvent.EmailChanged(it))
                            },
                            onPasswordFieldChange = {
                                loginViewModel.obtainEvent(LoginEvent.PasswordChanged(it))
                            },
                            onForgetClick = {
                                loginViewModel.obtainEvent(LoginEvent.ForgetClicked)
                            },
                            onLoginClick = {
                                loginViewModel.obtainEvent(LoginEvent.LoginClicked)
                            },
                        )
                    }
                    LoginSubState.SignUpStart ->
                        SignUpStartView(
                            viewState = this@with,
                            onEmailFieldChange = {
                                loginViewModel.obtainEvent(LoginEvent.EmailChanged(it))
                            },
                            onFirstNameFieldChange =  {
                                loginViewModel.obtainEvent(LoginEvent.FirstNameChanged(it))
                            },
                            onLastNameFieldChange =  {
                                loginViewModel.obtainEvent(LoginEvent.LastNameChanged(it))
                            },
                            onRegisterClick = {
                                loginViewModel.obtainEvent(LoginEvent.RegisterClicked)
                            },
                        )
                    LoginSubState.SignUpEnd-> {
                        SignUpEndView(
                            viewState=this@with,
                            onPasswordFieldChange = {
                                loginViewModel.obtainEvent(LoginEvent.PasswordChanged(it))
                            },
                            onPasswordConfirmFieldChange = {
                                loginViewModel.obtainEvent(LoginEvent.ConfirmPasswordChanged(it))
                            },
                            onRegisterClick = {
                                loginViewModel.obtainEvent(LoginEvent.RegisterClicked)
                            }
                        )
                    }
                    LoginSubState.Forgot -> ForgotView()
                }
            LabelWithLinkComponent(
                    labelText = when (loginSubState){
                        LoginSubState.SignUpStart -> stringResource(id = R.string.sign_up_change)
                        LoginSubState.SignUpEnd -> stringResource(id = R.string.sign_up_change)
                        LoginSubState.SignIn -> stringResource(id = R.string.sign_in_change)
                        else -> ""
                    },
                    linkText = when (loginSubState){
                        LoginSubState.SignUpStart -> stringResource(id = R.string.sign_up_change_link)
                        LoginSubState.SignUpEnd -> stringResource(id = R.string.sign_up_change_link)
                        LoginSubState.SignIn -> stringResource(id = R.string.sign_in_change_link)
                        else -> ""
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 24.dp)
            ) {
                    loginViewModel.obtainEvent(LoginEvent.ActionClicked)
                }
            }

    }
}