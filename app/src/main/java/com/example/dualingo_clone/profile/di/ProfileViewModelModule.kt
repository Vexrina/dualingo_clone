package com.example.dualingo_clone.profile.di

import com.example.dualingo_clone.database.data.DatabaseImpl
import com.example.dualingo_clone.profile.ui.ProfileViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProfileViewModelModule {
    @Provides
    fun provideProfileScreenViewModel(db: DatabaseImpl): ProfileViewModel {
        return ProfileViewModel(db)
    }
}
