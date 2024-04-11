package com.example.dualingo_clone.dataclasses

import com.example.dualingo_clone.serializators.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class CompletedQuest(
    @Serializable(with = UUIDSerializer::class)
    val userId: UUID,
    @Serializable(with = UUIDSerializer::class)
    val questId: UUID,
)
