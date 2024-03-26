package com.example.dualingo_clone.onboard.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dualingo_clone.MyApplication
import com.example.dualingo_clone.onboard.data.RepositoryImpl
import com.example.dualingo_clone.onboard.domain.OnboardingItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OnboardingViewModel : ViewModel() {
    private val _onboardingItems = MutableStateFlow<List<OnboardingItem>>(emptyList())
    val onboardingItems: StateFlow<List<OnboardingItem>> = _onboardingItems

    init {
        loadOnboardingItems()
    }

    fun loadOnboardingItems() {
        viewModelScope.launch {
            val repo = RepositoryImpl(MyApplication.instance)
            val items = repo.getOnboardingItems()
            _onboardingItems.value = items
        }
    }

    fun navigateToNextOnboardingItem() {
        val currentItems = _onboardingItems.value
        val remainingItems = currentItems.drop(1)
        _onboardingItems.value = remainingItems
    }
}
