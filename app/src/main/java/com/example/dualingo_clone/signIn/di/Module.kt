package com.example.dualingo_clone.signIn.di

import com.example.dualingo_clone.database.data.DatabaseImpl
import com.example.dualingo_clone.signIn.ui.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {
    @Provides
    fun provideLoginViewModel(db: DatabaseImpl): LoginViewModel {
        return LoginViewModel(db)
    }
}