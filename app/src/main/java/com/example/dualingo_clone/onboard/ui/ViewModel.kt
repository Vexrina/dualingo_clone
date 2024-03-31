package com.example.dualingo_clone.onboard.ui

import android.health.connect.datatypes.units.Length
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dualingo_clone.MyApplication
import com.example.dualingo_clone.dataclasses.OnboardingItem
import com.example.dualingo_clone.onboard.data.RepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OnboardingViewModel : ViewModel() {
    private val _onboardingItems = MutableStateFlow<List<OnboardingItem>>(emptyList())
    val onboardingItems: StateFlow<List<OnboardingItem>> = _onboardingItems
    private val repo = RepositoryImpl(MyApplication.instance)

    init {
        loadOnboardingItems()
    }

    private fun loadOnboardingItems() {
        viewModelScope.launch {
            val items = repo.getOnboardingItems()
            _onboardingItems.value = items
        }
    }

    fun navigateToNextOnboardingItem() {
        val currentItems = _onboardingItems.value
        mark(currentItems.size)
        val remainingItems = currentItems.drop(1)
        _onboardingItems.value = remainingItems
    }

    fun mark(length: Int) {
        viewModelScope.launch {
            when(length){
                3 -> repo.markImage1()
                2 -> repo.markImage2()
                1 -> repo.markOnboardingComplete()
                else -> {}
            }

        }
    }
}
