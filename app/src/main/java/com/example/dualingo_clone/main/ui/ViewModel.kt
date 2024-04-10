package com.example.dualingo_clone.main.ui

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dualingo_clone.database.data.DatabaseImpl
import com.example.dualingo_clone.dataclasses.Excersise
import com.example.dualingo_clone.dataclasses.TopUsers
import com.example.dualingo_clone.dataclasses.User
import com.example.dualingo_clone.dataclasses.UserInfo
import com.example.dualingo_clone.main.data.MainScreenRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val db: DatabaseImpl):ViewModel(){
    private val _excersises = MutableStateFlow<List<Excersise>>(emptyList())
    val excersises: StateFlow<List<Excersise>> = _excersises
    private val repo = MainScreenRepoImpl(db)

    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user

    private val _userInfo = MutableStateFlow<UserInfo?>(null)
    val userInfo: StateFlow<UserInfo?> = _userInfo

    private val _image = MutableStateFlow<ImageBitmap?>(null)
    val image: StateFlow<ImageBitmap?> = _image

    private val _topUsers = MutableStateFlow<List<TopUsers>>(emptyList())
    val topUsers : StateFlow<List<TopUsers>> = _topUsers

    init {
        loadExcersises()
        getUserData()
        getTopUsers()
    }
    private fun loadExcersises() {
        viewModelScope.launch {
            _excersises.value = repo.getAvailableExcersises()
        }
    }

    private fun getUserData(){
        viewModelScope.launch(Dispatchers.IO) {
            val (usr, usrInf) = repo.getUserData()
            _userInfo.value = usrInf
            _user.value = usr
            val byteArray = URL(userInfo.value!!.imageURL).readBytes()
            _image.value = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size).asImageBitmap()
        }
    }

    private fun getTopUsers(){
        viewModelScope.launch(Dispatchers.IO) {
            val topUsrs = repo.getTopUsers()
            val newUsers = mutableListOf<TopUsers>()
            for (i in 0..2){
                val user = repo.getUserById(topUsrs[i].userId)
                Log.d("INFO", user.firstName)
                val byteArray = URL(topUsrs[i].imageURL).readBytes()
                val imgBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size).asImageBitmap()
                newUsers.add(TopUsers(
                    userId = topUsrs[i].userId,
                    imageURL = topUsrs[i].imageURL,
                    languageId = topUsrs[i].languageId,
                    points = topUsrs[i].points,
                    image = imgBitmap,
                    fullNames = "${user.firstName} ${user.lastName}"
                ))
            }
            _topUsers.value=newUsers
        }
    }
}