package com.example.dualingo_clone.motherLanguage.data

import android.util.Log
import com.example.dualingo_clone.cache.data.CacheProvider
import com.example.dualingo_clone.cache.domain.Cache
import com.example.dualingo_clone.database.data.DatabaseImpl
import com.example.dualingo_clone.dataclasses.Language
import com.example.dualingo_clone.motherLanguage.domain.MotherLanguageRepo

class MotherLanguageRepoImpl(private val db: DatabaseImpl) : MotherLanguageRepo {

    private val cache: Cache = CacheProvider.getCache()

    override suspend fun getAvailableLanguages(): List<Language> {
        return db.getLanguages()
    }

    override suspend fun getUserMotherLanguage(): Language? {
        val languages = db.getLanguages()
        val cachedLang = cache.getUserMotherLanguage()
        for (lang in languages) {
            if (lang.name == cachedLang) {
                return lang
            }
        }
        return null
    }

    override suspend fun setUserMotherLanguage(language: Language) {
        Log.d("LANG", "set ${language.name} is mother")
        cache.setUserMotherLanguage(language.name)
    }
}
