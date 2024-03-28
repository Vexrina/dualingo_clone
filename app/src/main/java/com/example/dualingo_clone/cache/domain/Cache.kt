package com.example.dualingo_clone.cache.domain

interface Cache {
    fun setUserMotherLanguage(languageId: Int)
    fun getUserMotherLanguage(): Int?

    fun markOnboardingComplete()
    fun isOnboardingComplete(): Boolean
}