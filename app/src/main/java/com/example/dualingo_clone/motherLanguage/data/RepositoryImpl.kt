package com.example.dualingo_clone.motherLanguage.data

import com.example.dualingo_clone.database.data.DatabaseImpl
import com.example.dualingo_clone.dataclasses.Language
import com.example.dualingo_clone.motherLanguage.domain.MotherLanguageRepo

class MotherLanguageRepoImpl(private val db: DatabaseImpl) : MotherLanguageRepo {

    override suspend fun getAvailableLanguages(): List<Language> {
        // Реализация получения списка доступных языков из источника данных
        return db.getLanguages()
    }

    override suspend fun getUserMotherLanguage(): Language? {
        // Реализация получения родного языка пользователя из источника данных
        return db.getUserMotherLanguage()
    }

    override suspend fun setUserMotherLanguage(language: Language) {
        // Реализация установки родного языка пользователя в источнике данных
        db.setUserMotherLanguage(language)
    }
}
