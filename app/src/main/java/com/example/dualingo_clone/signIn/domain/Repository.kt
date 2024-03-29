package com.example.dualingo_clone.signIn.domain

interface SignInRepository {
    suspend fun signIn(email: String, password: String): Boolean

    suspend fun signOut(): Boolean

    suspend fun changePassword(): Boolean

    suspend fun changeEmail(): Boolean

    suspend fun signInGoogle(): Boolean
}