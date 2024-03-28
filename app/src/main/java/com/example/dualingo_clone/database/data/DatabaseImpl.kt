package com.example.dualingo_clone.database.data

import android.util.Log
import com.example.dualingo_clone.database.domain.Database
import com.example.dualingo_clone.database.info.key
import com.example.dualingo_clone.database.info.url
import com.example.dualingo_clone.dataclasses.Language
import com.example.dualingo_clone.dataclasses.User
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseImpl @Inject constructor() : Database {
    private val supabaseURL: String = url
    private val supabaseKEY: String = key

    private var supabaseClient: SupabaseClient? = null
    init {
        createClient()
    }

    private fun createClient(){
        Log.d("INFO", "Client will be created")
        supabaseClient = createSupabaseClient(
            supabaseUrl = supabaseURL,
            supabaseKey = supabaseKEY,
        ) {install(Postgrest)}
        Log.d("INFO", "all good, client created")
    }


    override suspend fun signUp(userData: User): Boolean {
        // Регистрация пользователя в базе данных SupaBase
        // Возвращает true, если успешно, иначе false
        // Реализация опущена для примера
        return true
    }

    override suspend fun signIn(email: String, password: String): Boolean {
        // Вход пользователя в систему с заданным email и паролем
        // Возвращает true, если успешно, иначе false
        // Реализация опущена для примера
        return true
    }

    override suspend fun signOut(): Boolean {
        // Выход пользователя из системы
        // Возвращает true, если успешно, иначе false
        // Реализация опущена для примера
        return true
    }

    override suspend fun getUserData(): User? {
        // Получение данных о пользователе из базы данных SupaBase
        // Реализация опущена для примера
        return null
    }

    override suspend fun getLanguages(): List<Language> {
        return supabaseClient!!
            .from("languages")
            .select()
            .decodeList<Language>()
    }

    override suspend fun getUserMotherLanguage(): Language? {
        // Получение родного языка пользователя из базы данных SupaBase
        // Реализация опущена для примера
        return null
    }

    override suspend fun setUserMotherLanguage(language: Language) {
        // Установка родного языка пользователя в базе данных SupaBase
        // Реализация опущена для примера
    }
}