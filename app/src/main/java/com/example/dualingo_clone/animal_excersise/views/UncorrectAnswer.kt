package com.example.dualingo_clone.animal_excersise.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.unit.dp
import com.example.dualingo_clone.R
import com.example.dualingo_clone.animal_excersise.models.AnimalExcersiseViewState
import com.example.dualingo_clone.ui.components.AnimalImage
import com.example.dualingo_clone.ui.components.ButtonComponent

@Composable
fun AnimalUncorrectView(
    viewState: AnimalExcersiseViewState,
    onNextClick: ()->Unit,
    onTryAgainClick: ()->Unit,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimalImage(
            modifier = Modifier.padding(start = 20.dp),
            smileModifier = Modifier,
            correctAnswer = viewState.questValue.answer1,
        )
        ButtonComponent(
            text = stringResource(id = R.string.NextButton),
            modifier = Modifier
                .padding(top=20.dp)
                .width(327.dp)
                .height(56.dp),
            onClick = onNextClick
        )
        ButtonComponent(
            text = stringResource(id = R.string.TryAgainButton),
            modifier = Modifier
                .padding(top=12.dp)
                .width(327.dp)
                .height(56.dp),
            onClick = onTryAgainClick
        )
    }

}