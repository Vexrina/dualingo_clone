package com.example.dualingo_clone.word_excersise.domain

import com.example.dualingo_clone.dataclasses.User
import com.example.dualingo_clone.dataclasses.UserInfo
import com.example.dualingo_clone.dataclasses.Word

interface WordExcersise {
    suspend fun getRandomExcersise(): List<Word>
    suspend fun getUserData(): Pair<User, UserInfo>
    suspend fun getUser(): User
    suspend fun updatePoints(userInfo: UserInfo)
}