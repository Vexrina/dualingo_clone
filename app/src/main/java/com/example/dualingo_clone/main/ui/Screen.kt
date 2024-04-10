package com.example.dualingo_clone.main.ui

import android.graphics.BitmapFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.dualingo_clone.R
import com.example.dualingo_clone.ui.components.BoldText
import com.example.dualingo_clone.ui.components.ExcersiseCard
import com.example.dualingo_clone.ui.components.HeaderMainScreen
import com.example.dualingo_clone.ui.components.TopUserItem

@Composable
fun MainScreen(navController: NavController){
    val viewModel: MainScreenViewModel = hiltViewModel()

    val excersises by viewModel.excersises.collectAsState()
    val user by viewModel.user.collectAsState()
    val profileImage by viewModel.image.collectAsState()
    val topUsers by viewModel.topUsers.collectAsState()

    Scaffold(
        topBar = {
            HeaderMainScreen(
                userName = user.firstName,
                profileImage = profileImage
            ){
                navController.navigate("profile")
            }
        },
        content = {padding->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ){
                BoldText(
                    text = "Top users",
                    modifier = Modifier
                        .padding(padding)
                        .padding(start = 24.dp, top = 11.dp),
                    fontSize = 24,
                )
                if (topUsers.isNotEmpty()){
                for (usr in topUsers){
                    TopUserItem(
                        imageBitmap = usr.image!!,
                        userName = usr.fullNames!!,
                        points = usr.points,
                    )
                }}
                BoldText(
                    text = "Available excersises",
                    modifier = Modifier
                        .padding(start=24.dp, top=11.dp),
                    fontSize = 24,
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2)
                ){
                    items(excersises.size){
                        ExcersiseCard(
                            smile = excersises[it].smile,
                            text = excersises[it].text,
                            backgroundColor = excersises[it].backColor,
                            index = it,
                            ){
                            val destination = when(it%4){
                                0-> "animal_excersise"
                                1-> "word_excersise"
                                2-> "audition_excersise"
                                3-> "game_excersise"
                                else -> ""
                            }
                            navController.navigate(destination)
                        }
                    }
                }
            }
        }
    )
}