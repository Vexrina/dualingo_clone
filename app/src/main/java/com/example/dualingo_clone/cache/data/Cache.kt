package com.example.dualingo_clone.cache.data

import android.content.Context
import com.example.dualingo_clone.cache.domain.Cache
import java.util.UUID

class SharedPreferencesCache(private val context: Context) : Cache {
    private val PREFS_NAME = "MyPrefsFile"
    private val KEY_USER_MOTHER_LANGUAGE_ID = "userMotherLanguageId"
    private val KEY_ONBOARDING_COMPLETE = "onboarding_complete"
    private val KEY_IMAGE1_VIEWED = "image1_viewed"
    private val KEY_IMAGE2_VIEWED = "image2_viewed"
    private val KEY_USER_EMAIL = "user_email"
    private val KEY_USER_PWORD = "user_pword"

    override fun setUserMotherLanguage(languageName: String) {
        val editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(KEY_USER_MOTHER_LANGUAGE_ID, languageName)
        editor.apply()
    }

    override fun getUserMotherLanguage(): String? {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_USER_MOTHER_LANGUAGE_ID, null)
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

    override fun markImage1Viewed() {
        val editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
        editor.putBoolean(KEY_IMAGE1_VIEWED, true)
        editor.apply()
    }

    override fun isImage1Viewed(): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_IMAGE1_VIEWED, false)
    }

    override fun markImage2Viewed() {
        val editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
        editor.putBoolean(KEY_IMAGE2_VIEWED, true)
        editor.apply()
    }

    override fun isImage2Viewed(): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_IMAGE2_VIEWED, false)
    }

    override fun userSignIn(email:String, password:String){
        val editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(KEY_USER_EMAIL, email)
        editor.putString(KEY_USER_PWORD, password)
        editor.apply()
    }

    override fun userSignOut(){
        val editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
        editor.remove(KEY_USER_EMAIL)
        editor.remove(KEY_USER_PWORD)
        editor.apply()
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