package com.example.dualingo_clone.signIn.data

import java.io.BufferedReader
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL
import com.example.dualingo_clone.signIn.domain.SignInRepository

class SignInRepositoryImpl : SignInRepository {

    override suspend fun signIn(email: String, password: String): Boolean {
        val url = URL("https://yourapp.supabase.co/auth/v1/token?grant_type=password")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.setRequestProperty("Content-Type", "application/json")
        connection.setRequestProperty("apikey", "your-api-key") // Замените "your-api-key" на ваш API ключ Supabase

        val requestBody = """
            {
                "email": "$email",
                "password": "$password"
            }
        """.trimIndent()

        connection.doOutput = true
        val outputStream = DataOutputStream(connection.outputStream)
        outputStream.write(requestBody.toByteArray())
        outputStream.flush()
        outputStream.close()

        val responseCode = connection.responseCode
        connection.inputStream.use { inputStream ->
            val response = inputStream.bufferedReader().use(BufferedReader::readText)
            return responseCode == HttpURLConnection.HTTP_OK
        }
    }

    override suspend fun signOut(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun changePassword(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun changeEmail(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun signInGoogle(): Boolean {
        TODO("Not yet implemented")
    }
}