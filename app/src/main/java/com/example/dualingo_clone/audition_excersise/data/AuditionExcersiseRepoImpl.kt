package com.example.dualingo_clone.audition_excersise.data

import com.example.dualingo_clone.audition_excersise.domain.AuditionExcersise
import com.example.dualingo_clone.cache.data.CacheProvider
import com.example.dualingo_clone.cache.domain.Cache
import com.example.dualingo_clone.database.data.DatabaseImpl
import com.example.dualingo_clone.dataclasses.User
import com.example.dualingo_clone.dataclasses.UserInfo
import com.example.dualingo_clone.dataclasses.Word

class AuditionExcersiseRepoImpl(private val db: DatabaseImpl): AuditionExcersise {
    private val cache: Cache = CacheProvider.getCache()
    override suspend fun updatePoints(userInfo: UserInfo) {
        db.updatePoints(userInfo)
    }

    override suspend fun getUserDate(): Pair<User, UserInfo> {
        val user = getUser()
        return db.getUserData(user)
    }

    override suspend fun getUser(): User {
        val (email, pwd) = cache.getUsersData()
        if (email == null || pwd == null) {
            return User()
        }
        val (user, err) = db.signIn(email, pwd)
        if (err != null) {
            return User()
        }
        return user
    }

    override suspend fun getRandomExcersise(): Word {
        return db.getRandomWords(1)[0]
    }
}