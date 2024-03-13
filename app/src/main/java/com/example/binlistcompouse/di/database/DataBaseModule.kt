package com.example.binlistcompouse.di.database

import android.content.Context
import com.example.database.AppDatabase
import com.example.database.BinListDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Provides
    @Singleton
    fun provideCharterDatabase(@ApplicationContext context: Context): AppDatabase{
        return BinListDataBase(context)
    }

}