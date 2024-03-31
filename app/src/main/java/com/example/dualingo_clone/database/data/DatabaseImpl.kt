package com.example.dualingo_clone.database.data

import android.util.Log
import com.example.dualingo_clone.database.domain.Database
import com.example.dualingo_clone.database.info.key
import com.example.dualingo_clone.database.info.url
import com.example.dualingo_clone.dataclasses.Language
import com.example.dualingo_clone.dataclasses.User
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.exceptions.BadRequestRestException
import io.github.jan.supabase.exceptions.HttpRequestException
import io.github.jan.supabase.exceptions.NotFoundRestException
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.exceptions.SupabaseEncodingException
import io.github.jan.supabase.exceptions.UnauthorizedRestException
import io.github.jan.supabase.exceptions.UnknownRestException
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import io.ktor.client.plugins.HttpRequestTimeoutException
import java.lang.Exception
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
        supabaseClient = createSupabaseClient(
            supabaseUrl = supabaseURL,
            supabaseKey = supabaseKEY,
        ) {install(Postgrest)}
    }


    override suspend fun signUp(userData: User): Pair<Boolean, String?> {
        try {
            supabaseClient!!
                .from("users")
                .insert(userData)
        } catch (e:BadRequestRestException){
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(false, "BadRequest")
        } catch (e: HttpRequestException){
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(false, "NetworkError")
        } catch (e: NotFoundRestException){
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(false, "Not found your resource")
        } catch (e: RestException){
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(false, "Unknown error")
        } catch (e: SupabaseEncodingException){
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(false, "Bad data for encode")
        } catch (e: UnauthorizedRestException){
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(false, "Please authorize")
        } catch (e: UnknownRestException){
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(false, "Unknown error")
        } catch (e: HttpRequestTimeoutException){
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(false, "Timeout exceeded")
        }
        return Pair(true, null)
    }

    override suspend fun signIn(email: String, password: String): Pair<User, String?> {
        val zeroUser = User("","","","","")
        try {
            val user: User = supabaseClient!!
                .from("users")
                .select(
                    columns=Columns.list(
                        "firstName",
                        "lastName",
                        "email",
                        "password",
                    )
                ) {
                    filter{
                        eq("email", email)
                        eq("password", password)
                    }
                }.decodeSingle<User>()
            return Pair(user, null)
        } catch (e:BadRequestRestException){
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(zeroUser, "BadRequest")
        } catch (e: HttpRequestException){
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(zeroUser, "NetworkError")
        } catch (e: NotFoundRestException){
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(zeroUser, "Not found your resource")
        } catch (e: RestException){
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(zeroUser, "Unknown error")
        } catch (e: SupabaseEncodingException){
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(zeroUser, "Bad data for encode")
        } catch (e: UnauthorizedRestException){
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(zeroUser, "Please authorize")
        } catch (e: UnknownRestException){
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(zeroUser, "Unknown error")
        } catch (e: HttpRequestTimeoutException){
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(zeroUser, "Timeout exceeded")
        }
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