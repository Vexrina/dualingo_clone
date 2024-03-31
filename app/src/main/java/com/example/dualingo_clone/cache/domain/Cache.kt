package com.example.dualingo_clone.cache.domain

interface Cache {
    fun setUserMotherLanguage(languageName: String)
    fun getUserMotherLanguage(): String?

    fun markOnboardingComplete()
    fun isOnboardingComplete(): Boolean
    fun isImage1Viewed(): Boolean
    fun markImage1Viewed()
    fun markImage2Viewed()
    fun isImage2Viewed(): Boolean
    fun userSignOut()
    fun userSignIn(email: String, password: String)
}