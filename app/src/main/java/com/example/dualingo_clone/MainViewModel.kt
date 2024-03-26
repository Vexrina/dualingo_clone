package com.example.dualingo_clone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dualingo_clone.onboard.data.RepositoryImpl
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _showOnboarding = MutableStateFlow(false)
    val showOnboarding: StateFlow<Boolean> = _showOnboarding

    init {
        checkOnboarding()
    }

    private fun checkOnboarding() {
        viewModelScope.launch {
            delay(2000)
            val onboardRepo = RepositoryImpl(MyApplication.instance)
            val onboardItems = onboardRepo.getOnboardingItems()

            val isOnboardingPassed = onboardItems.isEmpty()
            _showOnboarding.value = !isOnboardingPassed
            _isLoading.value = false
        }
    }

}