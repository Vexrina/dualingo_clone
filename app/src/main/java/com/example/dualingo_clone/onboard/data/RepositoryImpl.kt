package com.example.dualingo_clone.onboard.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import com.example.dualingo_clone.R
import com.example.dualingo_clone.onboard.domain.OnboardingItem
import com.example.dualingo_clone.onboard.domain.OnboardingRepository

class RepositoryImpl(private val context: Application) : OnboardingRepository {
    private fun isDarkTheme(): Boolean {
        return when (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> true
            else -> false
        }
    }

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("onboarding_preferences", Context.MODE_PRIVATE)
    }

    override suspend fun getOnboardingItems(): List<OnboardingItem> {
        val onboardingComplete = sharedPreferences.getBoolean("onboarding_complete", false)

        if (onboardingComplete) {
            return emptyList()
        }

        return listOf(
            OnboardingItem(
                imageResId = R.drawable.onboard_1,
                textBold = "Confidence in your words",
                text = "With conversation-based learning, you'll be talking from lesson one",
                buttonText = "Next",
            ),
            OnboardingItem(
                imageResId = R.drawable.onboard_2,
                textBold = "Take your time to learn",
                text = "Develop a habit of learning and make it a part of your daily routine",
                buttonText = "More",
            ),
            OnboardingItem(
                imageResId = if (isDarkTheme()) R.drawable.onboard_3_dt else R.drawable.onboard_3_lt,
                textBold = "The lessons you need to learn",
                text = "Using a variety of learning styles to learn and retain",
                buttonText = "Choose a language",
            ),
        )
    }

    override suspend fun markOnboardingComplete() {
        sharedPreferences.edit().putBoolean("onboarding_complete", true).apply()
    }
}