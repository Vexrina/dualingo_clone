package com.example.dualingo_clone.main.di

import com.example.dualingo_clone.database.data.DatabaseImpl
import com.example.dualingo_clone.main.ui.MainScreenViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {
    @Provides
    fun provideMainScreenViewModel(db: DatabaseImpl): MainScreenViewModel {
        return MainScreenViewModel(db)
    }
}
