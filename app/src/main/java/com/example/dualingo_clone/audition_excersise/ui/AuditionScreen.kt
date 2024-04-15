package com.example.dualingo_clone.audition_excersise.ui

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dualingo_clone.R
import com.example.dualingo_clone.ui.components.BoldText
import com.example.dualingo_clone.ui.components.ExcersiseCard
import com.example.dualingo_clone.ui.components.Header
import com.example.dualingo_clone.ui.components.TextComponent
import com.example.dualingo_clone.ui.theme.AppTheme

@Composable
fun AuditionExcersiseScreen(
    navController: NavController,
) {
    val viewModel: AuditionExcersiseViewModel = hiltViewModel()

    var canRecord by remember {mutableStateOf(false)}

    val recordAudioLauncher = rememberLauncherForActivityResult(
        contract =  ActivityResultContracts.RequestPermission(),
        onResult = {isGranted ->
            canRecord = isGranted
        }
    )


    LaunchedEffect(key1 = recordAudioLauncher){
        recordAudioLauncher.launch(Manifest.permission.RECORD_AUDIO)
    }

    val word by viewModel.word.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            Header(
                text = "Listening",
                modifier = Modifier
                    .padding(start = 12.dp),
                backIcon = true
            ) {
                navController.navigate("main")
            }
        },
        bottomBar = {
            if (!isLoading){
                Row(
                    modifier= Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(bottom = 54.dp)
                            ,
                    horizontalArrangement = Arrangement.Center
                ){
                    ExcersiseCard(
                        smile = "🎙️",
                        text = stringResource(id = R.string.checkSpeechButton),
                        backgroundColor = AppTheme.colors.excersise1,
                        index = 0,
                        radius = 50.dp,
                        width = 160.dp,
                        height = 160.dp,
                        animation = state.isSpeaking
                    ) {
                        if(state.spokenText.lowercase()==word.enWord.lowercase()) {
                            viewModel.next()
                        } else if (state.spokenText.isNotEmpty()){
                            viewModel.zeroStreak()
                        } else {
                            if (state.isSpeaking){
                                viewModel.stopListening()
                            } else viewModel.startListening()
                        }
                    }
                }
            }
        },
        content = {padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .background(Color.White)
                    .fillMaxSize(),
                verticalArrangement = if (!isLoading) Arrangement.Top else Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isLoading){
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(64.dp),
                        color = AppTheme.colors.excersise1,
                        trackColor = AppTheme.colors.excersise2
                    )
                } else {
                    BoldText(
                        text = word.enWord,
                        modifier = Modifier
                            .padding(top=30.dp),
                    )
                    TextComponent(text = word.transcription, modifier = Modifier)
                    BoldText(
                        text = stringResource(id = R.string.listeningHint),
                        modifier = Modifier
                            .width(320.dp)
                            .padding(top = 68.dp),
                        textAlign = TextAlign.Start
                    )
                    BoldText(
                        text =state.spokenText,
                        modifier = Modifier,
                        textColor = if(state.spokenText==word.enWord) AppTheme.colors.excersise4 else AppTheme.colors.excersise2
                    )
                    BoldText(
                        text = stringResource(id = R.string.listeningButtonHint),
                        modifier = Modifier
                            .width(320.dp)
                            .padding(top = 68.dp),
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    )
}