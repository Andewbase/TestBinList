package com.example.binlistcompouse.di.network

import com.example.binlistapi.RetrofitBankSource
import com.example.binlistapi.RetrofitBankSourceImplementation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RetrofitBankSourceModule {
    @Binds
    @Singleton
    fun provideRetrofitBankSource(retrofitBankSource: RetrofitBankSourceImplementation): RetrofitBankSource


}