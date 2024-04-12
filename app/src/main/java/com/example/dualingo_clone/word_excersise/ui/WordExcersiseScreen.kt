package com.example.dualingo_clone.word_excersise.ui

import android.util.Log
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dualingo_clone.R
import com.example.dualingo_clone.ui.components.ButtonComponent
import com.example.dualingo_clone.ui.components.Header
import com.example.dualingo_clone.ui.theme.AppTheme
import com.example.dualingo_clone.word_excersise.models.WordExcersiseEvent
import com.example.dualingo_clone.word_excersise.models.WordExcersiseSubState
import com.example.dualingo_clone.word_excersise.models.WordExcersiseViewState
import com.example.dualingo_clone.word_excersise.views.WordCorrectView
import com.example.dualingo_clone.word_excersise.views.WordQuestView

@Composable
fun WordExcersiseScreen(
    wordViewModel: WordExcersiseViewModel,
    navController: NavController,
) {
    val viewState: WordExcersiseViewState by wordViewModel
        .viewState
        .collectAsState(initial = WordExcersiseViewState())

    Log.d("Screen", "${viewState.wordSubState}")
    with(viewState) {
        Scaffold(
            topBar = {
                Header(
                    text = stringResource(id = R.string.wordExcersiseHeader),
                    backgroundColor = when (wordSubState) {
                        WordExcersiseSubState.Quest -> AppTheme.colors.splash
                        WordExcersiseSubState.Correct -> if (viewState.correctWord == viewState.selectedWord) AppTheme.colors.excersise4 else AppTheme.colors.excersise2
                    },
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
                        text = when (wordSubState) {
                            WordExcersiseSubState.Correct -> stringResource(id = R.string.nextButton)
                            WordExcersiseSubState.Quest -> stringResource(id = R.string.checkButton)
                        },
                        modifier = Modifier
                            .padding(bottom = 28.dp)
                            .width(328.dp)
                            .height(56.dp),
                        onClick = {
                            when (wordSubState) {
                                WordExcersiseSubState.Quest -> wordViewModel.obtainEvent(
                                    WordExcersiseEvent.CheckAnswer
                                )

                                else -> wordViewModel.obtainEvent(WordExcersiseEvent.NextQuest)
                            }
                        }
                    )
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
                    when (wordSubState) {
                        WordExcersiseSubState.Quest -> WordQuestView(
                            viewState = this@with,
                            onAnswerChanged = {
                                wordViewModel.obtainEvent(WordExcersiseEvent.AnswerChanged(it))
                            },
                        )

                        WordExcersiseSubState.Correct -> WordCorrectView(
                            viewState = this@with,
                        )
                    }
                }
            }
        )
    }
}