package com.example.dualingo_clone.signIn.domain

import com.example.dualingo_clone.dataclasses.User
import java.lang.Exception

interface LoginRepository {
    suspend fun signUp(user: User): Pair<Boolean, String?>

    suspend fun signIn(email: String, password: String): Pair<User, String?>

    suspend fun signInGoogle(): Boolean

    suspend fun signOut()

    suspend fun changePassword(): Boolean

    suspend fun changeEmail(): Boolean
    suspend fun signInToCache(email: String, password: String)
}