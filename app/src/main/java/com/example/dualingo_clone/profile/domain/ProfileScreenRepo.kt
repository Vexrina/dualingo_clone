package com.example.dualingo_clone.profile.domain

import android.graphics.Bitmap
import com.example.dualingo_clone.dataclasses.User

interface ProfileScreenRepo {
    suspend fun changeAvatar(avatar: Bitmap, user:User)

    suspend fun getUserData(): User
}