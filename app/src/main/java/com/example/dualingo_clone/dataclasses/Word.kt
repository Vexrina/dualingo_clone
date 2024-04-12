package com.example.dualingo_clone.dataclasses

import kotlinx.serialization.Serializable

@Serializable
data class Word(
    val enWord: String = "",
    val ruWord: String = "",
    val transcription: String = "",
)