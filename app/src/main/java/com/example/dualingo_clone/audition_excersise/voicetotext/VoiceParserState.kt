package com.example.dualingo_clone.audition_excersise.voicetotext

data class VoiceParserState(
    val spokenText: String = "",
    val isSpeaking: Boolean = false,
    val error: String? = null
)
