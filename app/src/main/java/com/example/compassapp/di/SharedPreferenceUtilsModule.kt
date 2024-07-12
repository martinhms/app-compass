package com.example.compassapp.di

import com.example.compassapp.utils.SharedPreferenceUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class SharedPreferenceUtilsModule {
    @Provides
    @Singleton
    fun provideSharedPreferenceUtils(): SharedPreferenceUtils {
        return SharedPreferenceUtils
    }
}