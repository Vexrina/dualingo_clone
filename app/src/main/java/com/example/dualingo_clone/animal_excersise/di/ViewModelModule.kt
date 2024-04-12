package com.example.dualingo_clone.animal_excersise.di

import com.example.dualingo_clone.animal_excersise.ui.AnimalExcersiseViewModel
import com.example.dualingo_clone.database.data.DatabaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {
    @Provides
    fun provideAnimalExcersiseViewModel(db: DatabaseImpl): AnimalExcersiseViewModel {
        return AnimalExcersiseViewModel(db)
    }
}
