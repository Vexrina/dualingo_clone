package com.example.dualingo_clone.database.data

import android.util.Log
import com.example.dualingo_clone.database.domain.Database
import com.example.dualingo_clone.database.info.key
import com.example.dualingo_clone.database.info.url
import com.example.dualingo_clone.dataclasses.CompletedQuest
import com.example.dualingo_clone.dataclasses.Language
import com.example.dualingo_clone.dataclasses.Quest
import com.example.dualingo_clone.dataclasses.TopUsers
import com.example.dualingo_clone.dataclasses.User
import com.example.dualingo_clone.dataclasses.UserInfo
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
import io.github.jan.supabase.postgrest.query.Order
import io.github.jan.supabase.postgrest.query.filter.FilterOperation
import io.github.jan.supabase.postgrest.query.filter.FilterOperator
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
import io.ktor.client.plugins.HttpRequestTimeoutException
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class DatabaseImpl @Inject constructor() : Database {
    private val supabaseURL: String = url
    private val supabaseKEY: String = key

    private var supabaseClient: SupabaseClient? = null

    init {
        createClient()
    }

    private fun createClient() {
        supabaseClient = createSupabaseClient(
            supabaseUrl = supabaseURL,
            supabaseKey = supabaseKEY,
        ) {
            install(Postgrest)
            install(Storage)
        }
    }

    override suspend fun signUp(userData: User): Pair<Boolean, String?> {
        try {
            supabaseClient!!
                .from("users")
                .insert(userData)
        } catch (e: BadRequestRestException) {
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(false, "BadRequest")
        } catch (e: HttpRequestException) {
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(false, "NetworkError")
        } catch (e: NotFoundRestException) {
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(false, "Not found your resource")
        } catch (e: RestException) {
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(false, "Unknown error")
        } catch (e: SupabaseEncodingException) {
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(false, "Bad data for encode")
        } catch (e: UnauthorizedRestException) {
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(false, "Please authorize")
        } catch (e: UnknownRestException) {
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(false, "Unknown error")
        } catch (e: HttpRequestTimeoutException) {
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(false, "Timeout exceeded")
        }
        return Pair(true, null)
    }

    override suspend fun signIn(email: String, password: String): Pair<User, String?> {
        val zeroUser = User()
        try {
            val user: User = supabaseClient!!
                .from("users")
                .select(
                    columns = Columns.list(
                        "id",
                        "firstName",
                        "lastName",
                        "email",
                        "password",
                    )
                ) {
                    filter {
                        eq("email", email)
                        eq("password", password)
                    }
                }.decodeSingle<User>()
            return Pair(user, null)
        } catch (e: BadRequestRestException) {
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(zeroUser, "BadRequest")
        } catch (e: HttpRequestException) {
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(zeroUser, "NetworkError")
        } catch (e: NotFoundRestException) {
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(zeroUser, "Not found your resource")
        } catch (e: RestException) {
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(zeroUser, "Unknown error")
        } catch (e: SupabaseEncodingException) {
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(zeroUser, "Bad data for encode")
        } catch (e: UnauthorizedRestException) {
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(zeroUser, "Please authorize")
        } catch (e: UnknownRestException) {
            Log.e("SUBC", "Error is ${e.message}")
            return Pair(zeroUser, "Unknown error")
        } catch (e: HttpRequestTimeoutException) {
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

    override suspend fun getUserData(user:User): Pair<User, UserInfo> {
        try {
            val userInfo: UserInfo = supabaseClient!!
                .from("usersInfo")
                .select() {
                    filter {
                        eq("userId", user.id!!)
                    }
                }
                .decodeSingle<UserInfo>()
            return Pair(user, userInfo)
        } catch (e:Exception){
            val newUser = supabaseClient!!
                .from("users")
                .select() {
                    filter {
                        eq("email", user.email)
                        eq("password", user.password)
                        eq("firstName", user.firstName)
                        eq("lastName", user.lastName)
                    }
                }
                .decodeSingle<User>()
            val userInfo: UserInfo = supabaseClient!!
                .from("usersInfo")
                .select() {
                    filter {
                        eq("userId", newUser.id!!)
                    }
                }
                .decodeSingle<UserInfo>()
            return Pair(user, userInfo)
        }
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

    override suspend fun uploadNewPic(byteArray: ByteArray, user: User): Pair<String, String?> {
        try {
            val uuid: UUID = user.id!!
            val buckets = supabaseClient!!.storage.from("users_avatars")

            val bucket = buckets.list().find{bucketItem -> uuid.toString() == bucketItem.name }

            if (bucket!=null){
                buckets.update(path=uuid.toString(), byteArray, upsert = false)
            } else{
                buckets.upload(path=uuid.toString(), byteArray, upsert = false)
            }

            val url = supabaseClient!!
                .storage
                .from("users_avatars")
                .publicUrl(uuid.toString())

            updatePicInTable(uuid, url)

            return Pair(url, "")
        } catch (e:Exception){
            Log.d("INFO", "${e.message}")
            return Pair("", e.message)
        }
    }

    override suspend fun updatePicInTable(uuid:UUID, url:String){
        // rewrite with upsert
        try{
            val userInfo = UserInfo(
                userId = uuid,
                imageURL = url,
                points = 0.0,
            )
            supabaseClient!!
                .from("usersInfo")
                .insert(userInfo)
        } catch (e:Exception){
            supabaseClient!!
                .from("usersInfo")
                .update({
                    set("imageURL", url)
                }){
                    filter {
                        eq("userId", uuid)
                    }
                }
        }
    }

    override suspend fun getTopUsers(): List<TopUsers>{
        val users = supabaseClient!!
            .from("usersInfo")
            .select(Columns.ALL){
                order("points", Order.DESCENDING)
                limit(3)
            }
            .decodeList<TopUsers>()
        return users
    }
    override suspend fun getUserById(id:UUID): User{
        return supabaseClient!!
            .from("users")
            .select(Columns.list(
                "firstName",
                "lastName",
            )){
                filter {
                    eq("id", id)
                }
            }.decodeSingle<User>()
    }

    override suspend fun setQuestCompleted(completedQuest: CompletedQuest, questType: String){
        supabaseClient!!
            .from("${questType}QuestCompleted")
            .insert(completedQuest)
    }

    override suspend fun updatePoints(user: UserInfo){
        supabaseClient!!
            .from("usersInfo")
            .upsert(user)

    }
    private fun filterQuests(quests: List<Quest>, completedQuests: List<CompletedQuest>): List<Quest>{
        val completedQuestIds = completedQuests.map { it.questId }
        return quests.filterNot { quest -> quest.id in completedQuestIds }
    }
    override suspend fun getRandomQuest(user: User, questType: String): Quest {
        try {
            val completedQuest = supabaseClient!!
                .from("${questType}QuestCompleted")
                .select(Columns.ALL){
                    filter {
                        eq("userId", user.id!!)
                    }
                }.decodeList<CompletedQuest>()
            Log.d("DB", "Completed quest size is ${completedQuest.size}")
            val quests = supabaseClient!!
                .from("${questType}Quest")
                .select(Columns.ALL){}
                .decodeList<Quest>()
            Log.d("DB", "Quests size is ${completedQuest.size}")
            val filteredQuest = filterQuests(quests, completedQuest)
            return filteredQuest[Random.nextInt(filteredQuest.size)]
        } catch (e: Exception){
            Log.d("DB", "Exception is ${e.message}")
            return Quest()
        }
    }
}