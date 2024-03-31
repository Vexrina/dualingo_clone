package com.example.dualingo_clone.onboard.domain

import com.example.dualingo_clone.dataclasses.OnboardingItem


interface OnboardingRepository {
    // Функция для получения списка элементов onboarding
    suspend fun getOnboardingItems(): List<OnboardingItem>

    // Функция для сохранения информации о том, что onboarding завершен
    suspend fun markOnboardingComplete()
    suspend fun markImage1()
    suspend fun markImage2()
}