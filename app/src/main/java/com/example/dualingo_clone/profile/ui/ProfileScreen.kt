package com.example.dualingo_clone.profile.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.dualingo_clone.profile.models.ProfileEvent
import com.example.dualingo_clone.profile.models.ProfileSubState
import com.example.dualingo_clone.profile.models.ProfileViewState
import com.example.dualingo_clone.profile.views.PhotoView
import com.example.dualingo_clone.profile.views.ProfileView

@Composable
fun ProfileScreen(
    profileViewModel: ProfileViewModel,
    navController: NavController,
){
    val viewState: ProfileViewState by profileViewModel.viewState.collectAsState(initial = ProfileViewState())

    with(viewState){
        when (profileSubState) {
            ProfileSubState.Profile -> {
                ProfileView(
                    viewState = this@with,
                    onChangeMotherLanguage = {
                        navController.navigate("motherLanguage")
                    },
                    onLogoutClicked = {},
                    onPhotoClicked = {
                        profileViewModel.obtainEvent(ProfileEvent.ActionClicked)
                    },
                    onSwitchDark = {},

                    )
            }

            ProfileSubState.Photo -> {
                PhotoView(
                    viewState=this@with,
                    onAcceptImage = {
                        profileViewModel.obtainEvent(ProfileEvent.UploadImage)
                        profileViewModel.obtainEvent(ProfileEvent.ActionClicked)
                    },
                    onBack = {
                        profileViewModel.obtainEvent(ProfileEvent.ActionClicked)
                    },
                    onUpdateImage = {
                        profileViewModel.obtainEvent(ProfileEvent.ImageChanged(it))
                    }
                )
            }
        }
    }
}