package com.example.dualingo_clone.motherLanguage.di

import com.example.dualingo_clone.database.data.DatabaseImpl
import com.example.dualingo_clone.motherLanguage.ui.MotherLanguageViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MotherLanguageViewModelModule {
    @Provides
    fun provideMotherLanguageViewModel(db: DatabaseImpl): MotherLanguageViewModel {
        return MotherLanguageViewModel(db)
    }
}
