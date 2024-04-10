package com.example.dualingo_clone.profile.data

import android.graphics.Bitmap
import com.example.dualingo_clone.cache.data.CacheProvider
import com.example.dualingo_clone.cache.domain.Cache
import com.example.dualingo_clone.database.data.DatabaseImpl
import com.example.dualingo_clone.dataclasses.User
import com.example.dualingo_clone.profile.domain.ProfileScreenRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.ByteArrayOutputStream
import java.net.URL

class ProfileScreenRepoImpl(private val db: DatabaseImpl): ProfileScreenRepo {
    private val cache: Cache = CacheProvider.getCache()

    override suspend fun changeAvatar(avatar: Bitmap, user: User){
        val stream = ByteArrayOutputStream()
        avatar.compress(Bitmap.CompressFormat.PNG, 90, stream)
        val avatarAsByteArray = stream.toByteArray()
        db.uploadNewPic(avatarAsByteArray, user)
    }
    override suspend fun getUserData(): User{
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