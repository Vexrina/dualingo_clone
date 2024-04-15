package com.example.dualingo_clone.audition_excersise.di

import com.example.dualingo_clone.audition_excersise.ui.AuditionExcersiseViewModel
import com.example.dualingo_clone.database.data.DatabaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuditionExcersiseViewModelModule {
    @Provides
    fun provideAuditionExcersiseViewModel(db: DatabaseImpl): AuditionExcersiseViewModel {
        return AuditionExcersiseViewModel(db)
    }
}