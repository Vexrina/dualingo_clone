package com.example.dualingo_clone.dataclasses

import com.example.dualingo_clone.serializators.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class User(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID?=null,
    val firstName: String = "",
    val lastName: String = "",
    val password: String = "",
    val email: String = "",
)
