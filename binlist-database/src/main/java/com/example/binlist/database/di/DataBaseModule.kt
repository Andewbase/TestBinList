package com.example.binlist.database.di

import android.content.Context
import androidx.room.Room
import com.example.binlist.database.BankCardInfoDao
import com.example.binlist.database.BankCardInfoDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideCharterDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            BankCardInfoDataBase::class.java,
            "bankcard_info_database")
            .build()

    @Provides
    @Singleton
    fun provideBankDao(appDataBase: BankCardInfoDataBase): BankCardInfoDao {
        return appDataBase.getBankCardInfoDao()
    }

}