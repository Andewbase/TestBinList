package com.example.binlistcompouse.di.repository

import com.example.binlistdata.BankCardRepository
import com.example.binlistdata.BankCardRepositoryImplementation
import com.example.binlistdata.Mapper
import com.example.binlistdata.MapperActions
import com.example.binlistdata.MapperActionsImplementation
import com.example.binlistdata.MapperImplementation
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindBankCardRepository(bankCardRepository: BankCardRepositoryImplementation): BankCardRepository

    @Binds
    fun bindMapper(mapper: MapperImplementation): Mapper

    @Binds
    fun bindMapperActions(mapperActions: MapperActionsImplementation): MapperActions
}