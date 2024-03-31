package com.example.dualingo_clone

import android.app.Application
import com.example.dualingo_clone.cache.data.CacheProvider
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        CacheProvider.init(this)
        _instance = this
    }

    companion object {
        private var _instance: MyApplication? = null
        val instance get() = requireNotNull(_instance)
    }
}