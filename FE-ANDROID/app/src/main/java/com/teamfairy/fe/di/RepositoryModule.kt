package com.teamfairy.fe.di

import com.teamfairy.data.repositoryimpl.CommunityRepositoryImpl
import com.teamfairy.data.repositoryimpl.IncomeRepositoryImpl
import com.teamfairy.data.repositoryimpl.SignInRepositoryImpl
import com.teamfairy.data.repositoryimpl.UserInfoRepositoryImpl
import com.teamfairy.domain.repository.CommunityRepository
import com.teamfairy.domain.repository.IncomeRepository
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


    @Binds
    @Singleton
    abstract fun bindIncomeInfoRepository(repositoryImpl: IncomeRepositoryImpl): IncomeRepository

    @Binds
    @Singleton
    abstract fun bindCommunityInfoRepository(repositoryImpl: CommunityRepositoryImpl): CommunityRepository
}