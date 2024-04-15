package com.example.dualingo_clone.audition_excersise.domain

import com.example.dualingo_clone.dataclasses.User
import com.example.dualingo_clone.dataclasses.UserInfo
import com.example.dualingo_clone.dataclasses.Word

interface AuditionExcersise {
    suspend fun updatePoints(userInfo: UserInfo)
    suspend fun getUserDate():Pair<User, UserInfo>
    suspend fun getUser(): User
    suspend fun getRandomExcersise(): Word
}