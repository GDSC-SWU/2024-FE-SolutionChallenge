package com.teamfairy.fe.di

import com.teamfairy.data.datasource.CommunityDataSource
import com.teamfairy.data.datasource.IncomeDataSource
import com.teamfairy.data.datasource.SignInDataSource
import com.teamfairy.data.datasourceimpl.CommunityDataSourceImpl
import com.teamfairy.data.datasourceimpl.IncomeDataSourceImpl
import com.teamfairy.data.datasourceimpl.SignInDataSourceImpl
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

    @Singleton
    @Binds
    abstract fun providesIncomeDataSource(dataSourceImpl: IncomeDataSourceImpl): IncomeDataSource

    @Singleton
    @Binds
    abstract fun providesCommunityDataSource(dataSourceImpl: CommunityDataSourceImpl): CommunityDataSource
}