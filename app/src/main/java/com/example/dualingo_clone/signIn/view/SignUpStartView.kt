package com.example.dualingo_clone.signIn.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.dualingo_clone.signIn.models.LoginViewState
import com.example.dualingo_clone.ui.components.BoldText
import com.example.dualingo_clone.ui.components.ButtonComponent
import com.example.dualingo_clone.ui.components.TextInput
import com.example.dualingo_clone.ui.components.TextVisuals

@Composable
fun SignUpStartView(
    viewState: LoginViewState,
    onFirstNameFieldChange: (String) -> Unit,
    onLastNameFieldChange: (String) -> Unit,
    onEmailFieldChange: (String) -> Unit,
    onRegisterClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BoldText(
            text = "Create an Account",
            modifier = Modifier
                .padding(40.dp)
                .width(263.dp)
        )
        TextInput(
            header = "First Name",
            textFieldValue = viewState.firstNameValue,
            enabled = !viewState.isProgress,
            onTextFieldChange = {
                if (!viewState.isProgress) onFirstNameFieldChange.invoke(it)
            },
            modifier = Modifier
                .padding(top = 32.dp)
                .width(327.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            ),
        )
        TextInput(
            header = "Last Name",
            textFieldValue = viewState.lastNameValue,
            enabled = !viewState.isProgress,
            onTextFieldChange = {
                if (!viewState.isProgress) onLastNameFieldChange.invoke(it)
            },
            modifier = Modifier
                .padding(top = 24.dp)
                .width(327.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            ),
        )
        TextInput(
            header = "Email Address",
            textFieldValue = viewState.emailValue,
            enabled = !viewState.isProgress,
            onTextFieldChange = {
                if (!viewState.isProgress) onEmailFieldChange.invoke(it)
            },
            modifier = Modifier
                .padding(top = 32.dp)
                .width(327.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.clearFocus()
                }
            ),
        )
        ButtonComponent(
            text = "Continue",
            modifier = Modifier
                .padding(top = 32.dp)
                .height(56.dp)
                .width(327.dp)
        ) {
            if (!viewState.isProgress){
                onRegisterClick.invoke()
            }
        }
    }
}
