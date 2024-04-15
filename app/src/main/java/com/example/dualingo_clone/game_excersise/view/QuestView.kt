package com.example.dualingo_clone.game_excersise.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.dualingo_clone.dataclasses.Word
import com.example.dualingo_clone.ui.components.BoldText
import com.example.dualingo_clone.ui.components.ButtonComponent
import com.example.dualingo_clone.ui.components.TextComponent
import com.example.dualingo_clone.ui.theme.AppTheme
import com.example.dualingo_clone.word_excersise.models.WordExcersiseViewState

@Composable
fun GameQuestView(
    viewState: WordExcersiseViewState,
    onAnswerChanged: (Word) -> Unit,
){
    val quests = viewState.questValue
    val correctWord = viewState.correctWord
    val buttonModifier = Modifier
        .padding(top = 10.dp)
        .width(328.dp)
        .height(56.dp)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = if (viewState.isLoading) Arrangement.Center else Arrangement.Top
    ) {
        if (viewState.isLoading) {
            CircularProgressIndicator(
                progress = {viewState.progress},
                modifier = Modifier
                    .width(64.dp),
                color = AppTheme.colors.excersise1,
                trackColor = AppTheme.colors.excersise2,
            )
        } else {
            BoldText(
                text = if (viewState.enLang) correctWord.enWord else correctWord.ruWord,
                modifier = Modifier
                    .padding(top = 36.dp)
            )
            if (viewState.enLang) {
                TextComponent(
                    text = correctWord.transcription,
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 24.dp)
                )
            }
            for (quest in quests) {
                ButtonComponent(
                    text = if (viewState.enLang) quest.ruWord else quest.enWord,
                    modifier = buttonModifier,
                    buttonColors = if (quest == viewState.selectedWord) AppTheme.colors.button else AppTheme.colors.userListItem
                ) {
                    onAnswerChanged.invoke(quest)
                }
            }
        }
    }
}
