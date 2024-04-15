package com.example.dualingo_clone.game_excersise.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dualingo_clone.R
import com.example.dualingo_clone.game_excersise.view.GameCorrectView
import com.example.dualingo_clone.game_excersise.view.GameQuestView
import com.example.dualingo_clone.ui.components.ButtonComponent
import com.example.dualingo_clone.ui.components.Header
import com.example.dualingo_clone.ui.theme.AppTheme
import com.example.dualingo_clone.word_excersise.models.WordExcersiseEvent
import com.example.dualingo_clone.word_excersise.models.WordExcersiseSubState
import com.example.dualingo_clone.word_excersise.models.WordExcersiseViewState

@Composable
fun GameExcersiseScreen(
    navController: NavController,
    gameViewModel: GameExcersiseViewModel,
) {
    val viewState: WordExcersiseViewState by gameViewModel
        .viewState
        .collectAsState(initial = WordExcersiseViewState())

    with(viewState) {
        Scaffold(
            topBar = {
                Header(
                    text = stringResource(id = R.string.gameExcersise),
                    backgroundColor = AppTheme.colors.splash,
                    modifier = Modifier.padding(start = 20.dp),
                    backIcon = true,
                ) {
                    navController.navigate("main")
                }
            },
            bottomBar = {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding()
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    ButtonComponent(
                        text = stringResource(id = R.string.nextButton),
                        modifier = Modifier
                            .padding(bottom = 28.dp)
                            .width(328.dp)
                            .height(56.dp),
                        onClick = {
                            when (wordSubState) {
                                WordExcersiseSubState.Quest -> gameViewModel.obtainEvent(
                                    WordExcersiseEvent.CheckAnswer
                                )

                                else -> gameViewModel.obtainEvent(WordExcersiseEvent.NextQuest)
                            }
                        }
                    )
                }
            },
            content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {
                    when (wordSubState) {
                        WordExcersiseSubState.Quest -> GameQuestView(
                            viewState = this@with,
                            onAnswerChanged = {
                                gameViewModel.obtainEvent(WordExcersiseEvent.AnswerChanged(it))
                            },
                        )
                        WordExcersiseSubState.Correct -> GameCorrectView(
                            viewState = this@with
                        )
                    }
                }
            }
        )
    }
}