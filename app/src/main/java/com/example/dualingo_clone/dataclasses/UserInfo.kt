package com.example.dualingo_clone.dataclasses

import com.example.dualingo_clone.serializators.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UserInfo(
    @Serializable(with = UUIDSerializer::class)
    val userId: UUID,
    val languageId: String? = "",
    val imageURL: String = "",
    val points: Double = 0.0,
)
