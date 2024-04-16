package com.example.dualingo_clone.cache.data

import android.content.Context
import com.example.dualingo_clone.cache.domain.Cache

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

    /**
     * Mark all onboard complete
     */
    override fun markOnboardingComplete() {
        val editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
        editor.putBoolean(KEY_ONBOARDING_COMPLETE, true)
        editor.apply()
    }

    /**
     * Check completed onboard
     * @return boolean status
     */
    override fun isOnboardingComplete(): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_ONBOARDING_COMPLETE, false)
    }

    /**
     * Mark first image from onboard as viewed
     */
    override fun markImage1Viewed() {
        val editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
        editor.putBoolean(KEY_IMAGE1_VIEWED, true)
        editor.apply()
    }

    /**
     * Check first image from onboard: viewed or not?
     * @return status of first onboard
     */
    override fun isImage1Viewed(): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_IMAGE1_VIEWED, false)
    }

    /**
     * Mark second image from onboard as viewed
     */
    override fun markImage2Viewed() {
        val editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
        editor.putBoolean(KEY_IMAGE2_VIEWED, true)
        editor.apply()
    }

    /**
     * Check second image from onboard: viewed or not?
     * @return status of second onboard
     */
    override fun isImage2Viewed(): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_IMAGE2_VIEWED, false)
    }

    /**
     * Put user email and pwd to SharedPreferences
     * @param email user's email
     * @param password user's password
     */
    override fun userSignIn(email: String, password: String) {
        val editor = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit()
        editor.putString(KEY_USER_EMAIL, email)
        editor.putString(KEY_USER_PWORD, password)
        editor.apply()
    }

    /**
     * Get users data from SharedPreferences
     * @return Pair(usersEmail, usersPassword)
     */
    override fun getUsersData(): Pair<String?, String?>{
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val email = prefs.getString(KEY_USER_EMAIL, "")
        val pwd = prefs.getString(KEY_USER_PWORD, "")
        return Pair(email, pwd)
    }

    /**
     * Remove users data from SharedPreferences
     */
    override fun userSignOut() {
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