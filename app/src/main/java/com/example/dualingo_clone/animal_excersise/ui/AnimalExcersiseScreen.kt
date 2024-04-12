package com.example.dualingo_clone.animal_excersise.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dualingo_clone.R
import com.example.dualingo_clone.animal_excersise.models.AnimalExcersiseEvent
import com.example.dualingo_clone.animal_excersise.models.AnimalExcersiseSubState
import com.example.dualingo_clone.animal_excersise.models.AnimalExcersiseViewState
import com.example.dualingo_clone.animal_excersise.views.AnimalCorrectView
import com.example.dualingo_clone.animal_excersise.views.AnimalQuestView
import com.example.dualingo_clone.animal_excersise.views.AnimalUncorrectView
import com.example.dualingo_clone.ui.components.Header
import com.example.dualingo_clone.ui.theme.AppTheme

@Composable
fun AnimalExcersiseScreen(
    animalViewModel: AnimalExcersiseViewModel,
    navController: NavController,
) {
    val viewState: AnimalExcersiseViewState by animalViewModel
        .viewState
        .collectAsState(initial = AnimalExcersiseViewState())

    Log.d("Screen", "${viewState.animalSubState}")
    with(viewState) {
        Scaffold(
            topBar = {
                Header(
                    text = stringResource(id = R.string.animalExcersiseHeader),
                    backgroundColor = when (animalSubState) {
                        AnimalExcersiseSubState.Quest -> AppTheme.colors.splash
                        AnimalExcersiseSubState.Correct -> AppTheme.colors.excersise4
                        AnimalExcersiseSubState.Uncorrect -> AppTheme.colors.excersise2
                    },
                    modifier = Modifier.padding(start=20.dp),
                    backIcon = true,
                ) {
                    navController.navigate("main")
                }
            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    when (animalSubState){
                        AnimalExcersiseSubState.Quest -> AnimalQuestView(
                            viewState = this@with,
                            onAnswerChanged = {
                                animalViewModel.obtainEvent(AnimalExcersiseEvent.AnswerChanged(it))
                            },
                            onCheckClick = {
                                animalViewModel.obtainEvent(AnimalExcersiseEvent.CheckAnswer)
                            }
                        )
                        AnimalExcersiseSubState.Correct -> AnimalCorrectView(
                            onNextClick = {
                                animalViewModel.obtainEvent(AnimalExcersiseEvent.NextQuest)
                            }
                        )
                        AnimalExcersiseSubState.Uncorrect -> AnimalUncorrectView(
                            viewState = this@with,
                            onNextClick = {

                            },
                            onTryAgainClick = {
                                animalViewModel.obtainEvent(AnimalExcersiseEvent.TryAgain)
                            }
                        )
                    }
                }
            }
        )
    }
}