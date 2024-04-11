package com.example.dualingo_clone.dataclasses

import androidx.compose.ui.graphics.ImageBitmap
import com.example.dualingo_clone.serializators.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Quest(
    @Serializable(with = UUIDSerializer::class)
    val id: UUID? = null,
    val imgURL: String="",
    val answer1: String="",
    val answer2: String="",
    val answer3: String="",
    val answer4: String="",
    val correct: Int=0,
    val imageBitmap: ImageBitmap? = null,
)
