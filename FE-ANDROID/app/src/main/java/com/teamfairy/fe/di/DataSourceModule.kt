package com.teamfairy.fe.di

import com.teamfairy.data.datasource.SharedPreferenceDataSource
import com.teamfairy.data.datasource.SignInDataSource
import com.teamfairy.data.datasourceimpl.SharedPreferenceDataSourceImpl
import com.teamfairy.data.datasourceimpl.SignInDataSourceImpl
import com.teamfairy.data.repositoryimpl.UserInfoRepositoryImpl
import com.teamfairy.domain.repository.UserInfoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun providesHomeDataSource(dataSourceImpl: SignInDataSourceImpl): SignInDataSource
}