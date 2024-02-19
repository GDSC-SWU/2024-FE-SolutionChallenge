package com.teamfairy.fe.di

import com.teamfairy.data.api.SignInApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideBookMarkService(@FeRetrofit retrofit: Retrofit): SignInApiService =
        retrofit.create(SignInApiService::class.java)
}