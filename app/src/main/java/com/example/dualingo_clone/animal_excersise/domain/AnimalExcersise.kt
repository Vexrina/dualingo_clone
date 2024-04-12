package com.example.dualingo_clone.animal_excersise.domain

import com.example.dualingo_clone.dataclasses.Quest
import com.example.dualingo_clone.dataclasses.User
import com.example.dualingo_clone.dataclasses.UserInfo

interface AnimalExcersise {
    suspend fun getRandomExcersise(user:User):Quest
    suspend fun setCompleteExcersise(user: UserInfo, quest: Quest)
    suspend fun getUserData(): Pair<User, UserInfo>
    suspend fun getUser(): User
}