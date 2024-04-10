package com.example.dualingo_clone.database.domain

import com.example.dualingo_clone.dataclasses.Language
import com.example.dualingo_clone.dataclasses.TopUsers
import com.example.dualingo_clone.dataclasses.User
import com.example.dualingo_clone.dataclasses.UserInfo
import java.util.UUID


interface Database {
    suspend fun signUp(userData: User): Pair<Boolean, String?>
    suspend fun signIn(email: String, password: String): Pair<User, String?>
    suspend fun signOut(): Boolean
    suspend fun getUserData(user:User): Pair<User, UserInfo>
    suspend fun getLanguages(): List<Language>
    suspend fun getUserMotherLanguage(): Language?
    suspend fun setUserMotherLanguage(language: Language)
    suspend fun uploadNewPic(byteArray: ByteArray, user: User): Pair<String, String?>
    suspend fun updatePicInTable(uuid: UUID, url: String)

    suspend fun getTopUsers(): List<TopUsers>
    suspend fun getUserById(id: UUID): User
}