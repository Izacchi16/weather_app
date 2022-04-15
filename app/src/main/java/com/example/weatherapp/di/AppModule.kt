package com.example.weatherapp.di

import android.app.Application
import android.content.Context
import com.example.weatherapp.api.ApiClient
import com.example.weatherapp.preference.PreferenceStorage
import com.example.weatherapp.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(
        apiClient: ApiClient,
        preferenceStorage: PreferenceStorage
    ): WeatherRepository {
        return WeatherRepository(apiClient, preferenceStorage)
    }

    @Singleton
    @Provides
    fun provideApiClient(): ApiClient {
        return ApiClient()
    }

    @Singleton
    @Provides
    fun providePreferenceStorage(context: Context): PreferenceStorage {
        return PreferenceStorage(context)
    }
}