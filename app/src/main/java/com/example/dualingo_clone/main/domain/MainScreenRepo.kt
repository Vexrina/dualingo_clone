package com.example.dualingo_clone.main.domain

import com.example.dualingo_clone.dataclasses.Excersise
import com.example.dualingo_clone.dataclasses.TopUsers
import com.example.dualingo_clone.dataclasses.User
import com.example.dualingo_clone.dataclasses.UserInfo
import java.util.UUID

interface MainScreenRepo {
    suspend fun getAvailableExcersises(): List<Excersise>
    suspend fun getUserData(): Pair<User, UserInfo?>
    suspend fun getTopUsers(): List<TopUsers>
    suspend fun getUserById(id: UUID): User
}