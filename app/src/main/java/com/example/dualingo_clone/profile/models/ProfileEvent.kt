package com.example.dualingo_clone.profile.models

import android.graphics.Bitmap

sealed class ProfileEvent {
    object None: ProfileEvent()
    object ActionClicked : ProfileEvent()
    object UploadImage : ProfileEvent()
    data class ImageChanged(val value: Bitmap?) : ProfileEvent()
}