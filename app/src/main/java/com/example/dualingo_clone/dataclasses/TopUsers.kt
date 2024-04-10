package com.example.dualingo_clone.dataclasses

import androidx.compose.ui.graphics.ImageBitmap
import com.example.dualingo_clone.serializators.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class TopUsers(
    @Serializable(with = UUIDSerializer::class)
    val userId: UUID,
    val imageURL: String,
    val languageId: String?,
    val points:Int,
    val image: ImageBitmap? = null,
    val fullNames: String? = "",
)
