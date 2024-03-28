package com.example.dualingo_clone.dataclasses

import com.example.dualingo_clone.serializators.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Language(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID,
    val name: String,
    val code: String
)

