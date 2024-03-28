package com.example.dualingo_clone.motherLanguage.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dualingo_clone.database.data.DatabaseImpl
import com.example.dualingo_clone.dataclasses.Language
import com.example.dualingo_clone.motherLanguage.data.MotherLanguageRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MotherLanguageViewModel @Inject constructor(private var db: DatabaseImpl) : ViewModel() {

    private val _languages = MutableStateFlow<List<Language>>(emptyList())
    val languages: StateFlow<List<Language>> = _languages

    private val _selectedLanguage = MutableStateFlow<Language?>(null)
    val selectedLanguage: StateFlow<Language?> = _selectedLanguage

    init {
        loadLanguages()
    }

    private fun loadLanguages() {
        viewModelScope.launch {
            val repo = MotherLanguageRepoImpl(db)
            val loadedLanguages = repo.getAvailableLanguages()
            _languages.value = loadedLanguages
        }
    }

    fun setSelectedLanguage(language: Language) {
        _selectedLanguage.value = language
    }

    fun isActiveLanguage(language: String): Boolean{
        return _selectedLanguage.value?.name.equals(language)
    }
}