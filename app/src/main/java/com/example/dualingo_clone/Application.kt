package com.example.dualingo_clone

import android.app.Application


class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        _instance = this
    }

    companion object {
        private var _instance: MyApplication? = null
        val instance get() = requireNotNull(_instance)
    }
}