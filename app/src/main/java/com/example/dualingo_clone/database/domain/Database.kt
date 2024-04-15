package com.example.dualingo_clone.database.domain

import com.example.dualingo_clone.dataclasses.CompletedQuest
import com.example.dualingo_clone.dataclasses.Language
import com.example.dualingo_clone.dataclasses.Quest
import com.example.dualingo_clone.dataclasses.TopUsers
import com.example.dualingo_clone.dataclasses.User
import com.example.dualingo_clone.dataclasses.UserInfo
import com.example.dualingo_clone.dataclasses.Word
import java.util.UUID


interface Database {
    suspend fun signUp(userData: User): Pair<Boolean, String?>
    suspend fun signIn(email: String, password: String): Pair<User, String?>
    suspend fun signOut(): Boolean
    suspend fun getUserData(user: User): Pair<User, UserInfo>
    suspend fun getLanguages(): List<Language>
    suspend fun getUserMotherLanguage(): Language?
    suspend fun setUserMotherLanguage(language: Language)
    suspend fun uploadNewPic(byteArray: ByteArray, user: User): Pair<String, String?>
    suspend fun updatePicInTable(uuid: UUID, url: String)

    suspend fun getTopUsers(): List<TopUsers>
    suspend fun getUserById(id: UUID): User
    suspend fun getRandomQuest(user: User, questType: String): Quest
    suspend fun setQuestCompleted(completedQuest: CompletedQuest, questType: String)
    suspend fun updatePoints(user: UserInfo)
    suspend fun getRandomWords(limit: Long = 4): List<Word>
}