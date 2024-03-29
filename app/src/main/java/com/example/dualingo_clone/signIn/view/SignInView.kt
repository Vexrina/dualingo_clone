package com.example.dualingo_clone.signIn.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dualingo_clone.R
import com.example.dualingo_clone.signIn.models.LoginViewState
import com.example.dualingo_clone.ui.components.BoldText
import com.example.dualingo_clone.ui.components.ButtonComponent
import com.example.dualingo_clone.ui.components.ImageComponent
import com.example.dualingo_clone.ui.components.TextInput
import com.example.dualingo_clone.ui.components.TextVisuals
import com.example.dualingo_clone.ui.theme.AppTheme
import com.example.dualingo_clone.ui.theme.fredokaFamily

@Composable
fun SignInView(
    viewState: LoginViewState,
    onEmailFieldChange: (String) -> Unit,
    onPasswordFieldChange: (String) -> Unit,
    onForgetClick: () -> Unit,
    onLoginClick: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ImageComponent(
            imageResId = R.drawable.login_image,
            modifier = Modifier
                .padding(24.dp)
                .width(180.dp)
                .height(100.dp)
        )
        BoldText(
            text = "For free, join now and start learning",
            modifier = Modifier
                .padding(12.dp)
                .width(263.dp)
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
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            ),
        )
        TextInput(
            header = "Password",
            textFieldValue = viewState.passwordValue,
            enabled = !viewState.isProgress,
            onTextFieldChange = {
                if(!viewState.isProgress) onPasswordFieldChange.invoke(it)
            },
            modifier = Modifier
                .padding(top = 24.dp)
                .width(327.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.clearFocus()
                }
            ),
            textVisuals = TextVisuals.Password
        )
        Text(
            text = "Forgot Password",
            modifier = Modifier
                .padding(top = 12.dp)
                .width(327.dp),
            style = TextStyle(
                color = AppTheme.colors.forgotPassword,
                fontFamily = fredokaFamily,
                fontSize = 15.sp
            ),
        )
        ButtonComponent(
            text = "Login",
            modifier = Modifier
                .padding(top = 32.dp)
                .height(56.dp)
                .width(327.dp)
        ) {

        }
    }
}