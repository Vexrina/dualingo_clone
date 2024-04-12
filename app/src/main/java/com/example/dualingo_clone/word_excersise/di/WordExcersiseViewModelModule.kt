package com.example.dualingo_clone.word_excersise.di

import com.example.dualingo_clone.database.data.DatabaseImpl
import com.example.dualingo_clone.word_excersise.ui.WordExcersiseViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object WordExcersiseViewModelModule {
    @Provides
    fun provideWordExcersiseViewModel(db: DatabaseImpl): WordExcersiseViewModel {
        return WordExcersiseViewModel(db)
    }
}
