package com.example.dualingo_clone.database.domain

import com.example.dualingo_clone.dataclasses.Language
import com.example.dualingo_clone.dataclasses.User


interface Database {
    suspend fun signUp(userData: User): Boolean
    suspend fun signIn(email: String, password: String): Boolean
    suspend fun signOut(): Boolean
    suspend fun getUserData(): User?

    suspend fun getLanguages(): List<Language>
    suspend fun getUserMotherLanguage(): Language?
    suspend fun setUserMotherLanguage(language: Language)
}