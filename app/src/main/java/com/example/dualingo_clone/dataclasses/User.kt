package com.example.dualingo_clone.dataclasses

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val firstName: String,
    val lastName: String,
    val password: String,
    val email: String,
    val motherLanguage: String? = null,
)
