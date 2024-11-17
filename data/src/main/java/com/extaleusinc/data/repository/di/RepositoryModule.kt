package com.extaleusinc.data.repository.di

import android.content.SharedPreferences
import com.extaleusinc.data.network.api.ApiService
import com.extaleusinc.data.repository.home.HomeRepository
import com.extaleusinc.data.repository.home.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal class RepositoryModule {

    @Provides
    fun provideHomeRepository(
        apiService: ApiService,
        preferences: SharedPreferences
    ): HomeRepository {
        return HomeRepositoryImpl(apiService, preferences)
    }
}
