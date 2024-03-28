package com.example.dualingo_clone.motherLanguage.domain

import com.example.dualingo_clone.dataclasses.Language

interface MotherLanguageRepo {

    // Получение списка доступных языков
    suspend fun getAvailableLanguages(): List<Language>

    // Получение родного языка пользователя
    suspend fun getUserMotherLanguage(): Language?

    // Установка родного языка пользователя
    suspend fun setUserMotherLanguage(language: Language)

}