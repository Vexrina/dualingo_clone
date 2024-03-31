package com.example.dualingo_clone.main.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.dualingo_clone.ui.components.BoldText
import com.example.dualingo_clone.ui.components.Header

@Composable
fun MainScreen(navController: NavController){
    Scaffold(
        topBar = {
            Header(
            text="Main screen",
            modifier = Modifier
                .width(375.dp)
                .padding(start = 21.dp),
        ){}
        },
        content = {padding->
            BoldText(text = "TODO :D", modifier = Modifier.padding(padding))
        }
    )
}