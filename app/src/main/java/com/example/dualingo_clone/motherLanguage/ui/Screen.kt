package com.example.dualingo_clone.motherLanguage.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dualingo_clone.ui.components.BoldText
import com.example.dualingo_clone.ui.components.ButtonComponent
import com.example.dualingo_clone.ui.components.Header
import com.example.dualingo_clone.ui.theme.AppTheme

@Composable
fun MotherLanguageScreen(navController: NavController) {
    val viewModel: MotherLanguageViewModel = hiltViewModel()

    val languages by viewModel.languages.collectAsState()
    val selectedLanguage by viewModel.selectedLanguage.collectAsState()

    Scaffold(
        topBar = {
            Header(
                text = "Language select",
                modifier = Modifier
                    .width(375.dp)
                    .padding(start = 21.dp)
            )
        },
        bottomBar = {
            ButtonComponent(
                text = "Choose",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .wrapContentHeight(Alignment.Bottom)
                    .padding(top = 12.dp, bottom = 26.dp, start = 24.dp, end = 24.dp)
                    .width(327.dp)
                    .height(67.dp)
            ) {
                navController.navigate("main")
            }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                BoldText(
                    text = "What is your Mother language?",
                    modifier = Modifier
                        .padding(top = 12.dp, bottom = 4.dp)
                )
                languages.forEach { language ->
                    ButtonComponent(
                        text = language.name,
                        textColor = Color.Black,
                        buttonColors =
                        if (selectedLanguage?.name == language.name)
                            AppTheme.colors.activeLanguage
                        else
                            AppTheme.colors.inactiveLanguage,
                        textAlign = TextAlign.Start,
                        radius = 20,
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .width(327.dp)
                            .height(67.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        viewModel.setSelectedLanguage(language)
                    }
                }
            }
        }
    )
}