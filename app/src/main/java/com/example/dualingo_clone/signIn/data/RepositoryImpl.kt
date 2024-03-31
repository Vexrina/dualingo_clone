package com.example.dualingo_clone.signIn.data

import com.example.dualingo_clone.cache.data.CacheProvider
import com.example.dualingo_clone.cache.domain.Cache
import com.example.dualingo_clone.database.data.DatabaseImpl
import com.example.dualingo_clone.dataclasses.User
import com.example.dualingo_clone.signIn.domain.LoginRepository
import java.io.BufferedReader
import java.io.DataOutputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class LoginRepositoryImpl(private val db: DatabaseImpl) : LoginRepository {

    private val cache: Cache = CacheProvider.getCache()
    override suspend fun signUp(user: User): Pair<Boolean, String?> {
        return db.signUp(user)
    }

    override suspend fun signIn(email: String, password: String): Pair<User, String?> {
        return db.signIn(email, password)
    }

    override suspend fun signInGoogle(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun signInToCache(email: String, password: String){
        cache.userSignIn(email, password)
    }
    override suspend fun signOut() {
        cache.userSignOut()
    }

    override suspend fun changePassword(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun changeEmail(): Boolean {
        TODO("Not yet implemented")
    }
}