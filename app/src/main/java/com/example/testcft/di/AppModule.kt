package com.example.testcft.di

import android.content.Context
import androidx.room.Room
import com.example.testcft.api.BankApi
import com.example.testcft.api.BankApi.Companion.BASE_URL
import com.example.testcft.data.cache.BankCardInfoDao
import com.example.testcft.data.cache.BankCardInfoDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun loggin() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun okHttpClient() = OkHttpClient.Builder().addInterceptor(loggin()).build()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()

    @Singleton
    @Provides
    fun provideCharacterApi(retrofit: Retrofit): BankApi =
        retrofit.create(BankApi::class.java)

    @Provides
    @Singleton
    fun provideCharterDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            BankCardInfoDataBase::class.java,
            "bankcardinfo_database")
            .build()

    @Provides
    @Singleton
    fun provideCharterDao(appDataBase: BankCardInfoDataBase): BankCardInfoDao {
        return appDataBase.getBankCardInfoDao()
    }

}