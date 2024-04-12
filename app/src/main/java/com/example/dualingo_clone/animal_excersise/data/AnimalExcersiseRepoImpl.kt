package com.example.dualingo_clone.animal_excersise.data

import com.example.dualingo_clone.animal_excersise.domain.AnimalExcersise
import com.example.dualingo_clone.cache.data.CacheProvider
import com.example.dualingo_clone.cache.domain.Cache
import com.example.dualingo_clone.database.data.DatabaseImpl
import com.example.dualingo_clone.dataclasses.CompletedQuest
import com.example.dualingo_clone.dataclasses.Quest
import com.example.dualingo_clone.dataclasses.User
import com.example.dualingo_clone.dataclasses.UserInfo

class AnimalExcersiseRepoImpl(private val db: DatabaseImpl):AnimalExcersise {
    private val cache: Cache = CacheProvider.getCache()

    override suspend fun getRandomExcersise(user: User): Quest {
        return db.getRandomQuest(user, "animal")
    }

    override suspend fun setCompleteExcersise(user: UserInfo, quest: Quest) {
        val completedQuest = CompletedQuest(user.userId, quest.id!!)
        db.setQuestCompleted(completedQuest, "animal")
        db.updatePoints(user)
    }

    override suspend fun getUserData(): Pair<User, UserInfo>{
        val user = getUser()
        return db.getUserData(user)
    }

    override suspend fun getUser(): User{
        val (email, pwd) = cache.getUsersData()
        if (email==null || pwd==null){
            return User()
        }
        val (user, err) = db.signIn(email, pwd)
        if (err!=null){
            return User()
        }
        return user
    }
}