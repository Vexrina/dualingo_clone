package com.example.dualingo_clone.onboard.data

import android.app.Application
import android.content.res.Configuration
import android.util.Log
import com.example.dualingo_clone.R
import com.example.dualingo_clone.cache.data.CacheProvider
import com.example.dualingo_clone.cache.domain.Cache
import com.example.dualingo_clone.dataclasses.OnboardingItem
import com.example.dualingo_clone.onboard.domain.OnboardingRepository

class RepositoryImpl(private val context: Application) : OnboardingRepository {
    private val cache: Cache = CacheProvider.getCache()
    private fun isDarkTheme(): Boolean {
        return when (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> true
            else -> false
        }
    }

    override suspend fun getOnboardingItems(): List<OnboardingItem> {
        val onboardingComplete = cache.isOnboardingComplete()

        if (onboardingComplete) {
            return emptyList()
        }

        val image1 = cache.isImage1Viewed()

        if (!image1) {
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
        val image2 = cache.isImage2Viewed()
        if (!image2){
            return listOf(
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
        return listOf(
            OnboardingItem(
                imageResId = if (isDarkTheme()) R.drawable.onboard_3_dt else R.drawable.onboard_3_lt,
                textBold = "The lessons you need to learn",
                text = "Using a variety of learning styles to learn and retain",
                buttonText = "Choose a language",
            ),
        )
    }

    override suspend fun markOnboardingComplete() {
        cache.markOnboardingComplete()
        Log.d("MARK", "All marked")
    }

    override suspend fun markImage2(){
        cache.markImage2Viewed()
        Log.d("MARK", "Second marked")
    }

    override suspend fun markImage1(){
        cache.markImage1Viewed()
        Log.d("MARK", "First marked")
    }
}