package com.teamfairy.fe.di

import com.teamfairy.data.repositoryimpl.SignInRepositoryImpl
import com.teamfairy.data.repositoryimpl.UserInfoRepositoryImpl
import com.teamfairy.domain.repository.SignInRepository
import com.teamfairy.domain.repository.UserInfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindsSignInRepository(repositoryImpl: SignInRepositoryImpl): SignInRepository

    @Binds
    @Singleton
    abstract fun bindUserInfoRepository(repositoryImpl: UserInfoRepositoryImpl): UserInfoRepository
}