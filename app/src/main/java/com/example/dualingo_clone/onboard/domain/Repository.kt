package com.example.dualingo_clone.onboard.domain


// Модель данных, с которой работает репозиторий
data class OnboardingItem(
    val imageResId: Int,
    val textBold: String,
    val text: String,
    val buttonText: String,
)

// Интерфейс репозитория
interface OnboardingRepository {
    // Функция для получения списка элементов onboarding
    suspend fun getOnboardingItems(): List<OnboardingItem>

    // Функция для сохранения информации о том, что onboarding завершен
    suspend fun markOnboardingComplete()
}