package com.example.dualingo_clone.profile.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.example.dualingo_clone.profile.models.ProfileViewState
import com.example.dualingo_clone.ui.components.ButtonComponent
import com.example.dualingo_clone.ui.components.HeaderProfile
import com.example.dualingo_clone.ui.theme.AppTheme

@Composable
fun ProfileView(
    viewState: ProfileViewState,
    onChangeMotherLanguage : () -> Unit,
    onPhotoClicked : () -> Unit,
    onLogoutClicked: () -> Unit,
    onSwitchDark: () -> Unit,
){
    Scaffold(
        topBar = {
            val username = viewState.user?.firstName?:"User1234567890"
            val image = viewState.image?.asImageBitmap()
            HeaderProfile(userName = username, profileImage = image)
        },
        content = {padding ->
            Column(
                modifier= Modifier
                    .background(Color.White)
                    .padding(padding)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ButtonComponent(
                    text = "Switch to Dark",
                    modifier = Modifier
                        .padding(top=16.dp)
                        .width(327.dp)
                        .height(56.dp),
                    onClick = onSwitchDark,
                )
                ButtonComponent(
                    text = "Change mother language",
                    modifier = Modifier
                        .padding(top=16.dp)
                        .width(327.dp)
                        .height(56.dp),
                    onClick = onChangeMotherLanguage,
                )
                ButtonComponent(
                    text = "Change your image",
                    modifier = Modifier
                        .padding(top=16.dp)
                        .width(327.dp)
                        .height(56.dp),
                    onClick = onPhotoClicked,
                )
                ButtonComponent(
                    text = "Logout",
                    modifier = Modifier
                        .padding(top=16.dp, bottom = 28.dp)
                        .width(327.dp)
                        .height(56.dp),
                    buttonColors = AppTheme.colors.userListItem,
                    onClick = onLogoutClicked,
                )
            }
        }
    )
}