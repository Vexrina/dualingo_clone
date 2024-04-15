package com.example.dualingo_clone.audition_excersise.voicetotext

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VoiceToTextParser(
    private val application: Application
): RecognitionListener {

    private val _state = MutableStateFlow(VoiceParserState())
    val state = _state.asStateFlow()

    val recogniser = SpeechRecognizer.createSpeechRecognizer(application)


    fun clearSpokenText(){
        _state.update {
            it.copy(
                spokenText = ""
            )
        }
    }
    fun startListening(languageCode: String){
        _state.update { VoiceParserState() }

        if (!SpeechRecognizer.isRecognitionAvailable(application)){
            _state.update {
                it.copy(
                    error = "recognition is not available"
                )
            }
        }

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply{
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM,
                )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, languageCode)
        }
        recogniser.setRecognitionListener(this)
        recogniser.startListening(intent)

        _state.update {
            it.copy(
                isSpeaking = true
            )
        }
    }
    fun stopListening(){
        _state.update {
            it.copy(
                isSpeaking = false
            )
        }
        recogniser.stopListening()
    }
    override fun onReadyForSpeech(params: Bundle?) {
        _state.update {
            it.copy(
                error = null
            )
        }
    }

    override fun onBeginningOfSpeech() = Unit

    override fun onRmsChanged(rmsdB: Float) = Unit

    override fun onBufferReceived(buffer: ByteArray?) = Unit

    override fun onEndOfSpeech() {
        _state.update {
            it.copy(
                isSpeaking = false
            )
        }
        Log.d("parser", "end speech")
    }

    override fun onError(error: Int) {
       if (error == SpeechRecognizer.ERROR_CLIENT){
           return
       }
        _state.update {
            it.copy(
                error="Error: $error"
            )
        }
    }

    override fun onResults(results: Bundle?) {
        Log.d("parser", "HELLO")
        results
            ?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
            ?.getOrNull(0)
            ?.let{ result->
                _state.update {
                    it.copy(
                        spokenText = result
                    )
                }
            }
        Log.d("parser", _state.value.spokenText)
    }

    override fun onPartialResults(partialResults: Bundle?) = Unit

    override fun onEvent(eventType: Int, params: Bundle?) =Unit
}