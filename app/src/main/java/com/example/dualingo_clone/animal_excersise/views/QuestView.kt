package com.example.dualingo_clone.animal_excersise.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.dualingo_clone.R
import com.example.dualingo_clone.animal_excersise.models.AnimalExcersiseViewState
import com.example.dualingo_clone.ui.components.AnimalImage
import com.example.dualingo_clone.ui.components.ButtonComponent
import com.example.dualingo_clone.ui.components.TextInput
import com.example.dualingo_clone.ui.theme.AppTheme

@Composable
fun AnimalQuestView(
    viewState: AnimalExcersiseViewState,
    onAnswerChanged: (String) -> Unit,
    onCheckClick: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val quest = viewState.questValue

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if (viewState.isLoading) Arrangement.Center else Arrangement.Top
    ) {
        if (viewState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(64.dp)
                    ,
                color = AppTheme.colors.excersise1,
                trackColor = AppTheme.colors.excersise2
            )
        } else {
            AnimalImage(
                modifier = Modifier
                    .padding(16.dp)
                    .size(328.dp),
                image = quest.imageBitmap
            )
            TextInput(
                header = stringResource(id = R.string.animalGuessInputHint),
                textFieldValue = viewState.answerValue,
                onTextFieldChange = {
                    if (!viewState.isProgress) onAnswerChanged.invoke(it)
                },
                modifier = Modifier
                    .padding(top = 16.dp)
                    .width(328.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.clearFocus()
                    }
                )
            )
            ButtonComponent(
                text = "Check",
                modifier = Modifier
                    .padding(top=16.dp)
                    .width(328.dp)
                    .height(56.dp),
                onClick = onCheckClick
            )
        }
    }

}