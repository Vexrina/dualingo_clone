package com.example.dualingo_clone.cache.data

import android.content.Context
import com.example.dualingo_clone.cache.domain.Cache

class SharedPreferencesCache(private val context: Context) : Cache {
    private val PREFS_NAME = "MyPrefsFile"
    private val KEY_USER_MOTHER_LANGUAGE_ID = "userMotherLanguageId"
    private val KEY_ONBOARDING_COMPLETE = "onboarding_complete"

    override fun setUserMotherLanguage(languageId: Int) {
        val editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
        editor.putInt(KEY_USER_MOTHER_LANGUAGE_ID, languageId)
        editor.apply()
    }

    override fun getUserMotherLanguage(): Int? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getInt(KEY_USER_MOTHER_LANGUAGE_ID, -1).takeIf { it != -1 }
    }

    override fun markOnboardingComplete() {
        val editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
        editor.putBoolean(KEY_ONBOARDING_COMPLETE, true)
        editor.apply()
    }

    override fun isOnboardingComplete(): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_ONBOARDING_COMPLETE, false)
    }
}

object CacheProvider {
    private lateinit var cache: Cache

    fun init(context: Context) {
        cache = SharedPreferencesCache(context)
    }

    fun getCache(): Cache {
        return cache
    }
}