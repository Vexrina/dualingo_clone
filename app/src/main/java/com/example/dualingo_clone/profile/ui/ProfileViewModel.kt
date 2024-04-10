package com.example.dualingo_clone.profile.ui

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dualingo_clone.common.EventHandler
import com.example.dualingo_clone.database.data.DatabaseImpl
import com.example.dualingo_clone.profile.data.ProfileScreenRepoImpl
import com.example.dualingo_clone.profile.models.ProfileEvent
import com.example.dualingo_clone.profile.models.ProfileSubState
import com.example.dualingo_clone.profile.models.ProfileViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val db: DatabaseImpl
): ViewModel(), EventHandler<ProfileEvent> {

    private val repo = ProfileScreenRepoImpl(db)

    private val _viewState: MutableStateFlow<ProfileViewState> = MutableStateFlow(ProfileViewState())
    val viewState: StateFlow<ProfileViewState> = _viewState
    init {
        viewModelScope.launch {
            val userData = repo.getUserData()
            _viewState.value = _viewState.value.copy(user = userData)
        }
    }

    override fun obtainEvent(event: ProfileEvent) {
        when(event){
            ProfileEvent.None -> TODO()
            ProfileEvent.ActionClicked -> switchActionState()
            ProfileEvent.UploadImage -> uploadImage()
            is ProfileEvent.ImageChanged -> imageChanged(event.value)
        }
    }

    private fun switchActionState(){
        val currentState = _viewState.value
        val newState = when (currentState.profileSubState){
            ProfileSubState.Profile -> currentState.copy(profileSubState = ProfileSubState.Photo)
            ProfileSubState.Photo -> currentState.copy(profileSubState = ProfileSubState.Profile)
        }
        _viewState.value = newState
    }

    private fun imageChanged(value: Bitmap?) {
        if (value!=null){
            _viewState.value = _viewState.value.copy(image = value)
        }
    }

    private fun uploadImage(){
        viewModelScope.launch {
            repo.changeAvatar(_viewState.value.image!!, _viewState.value.user!!)
        }
    }
}