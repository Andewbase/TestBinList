package com.example.binlistcompouse.di.network

import com.example.binlistapi.bank.BankApi
import com.example.binlistcompouse.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideBinListApi(): BankApi {
        return BankApi(BuildConfig.BIN_LIST_API_BASE_URL)
    }

}