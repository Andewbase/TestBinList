package com.example.binlist.api

import com.example.binlist.api.bank.BankApi
import com.example.binlist.api.bank.entities.BankCard
import com.example.binlist.api.base.BaseRetrofitSource
import com.example.binlist.api.base.RetrofitConfig
import javax.inject.Inject
import javax.inject.Singleton

interface RetrofitBankSource {

    suspend fun getBankInformation(number: String): BankCard


    @Singleton
    class Base @Inject constructor (config: RetrofitConfig) : BaseRetrofitSource(config),
        RetrofitBankSource {

        private val bankApi = retrofit.create(BankApi::class.java)

        override suspend fun getBankInformation(number: String): BankCard = wrapRetrofitExceptions {
            bankApi.getBankCardInformation(number)
        }

    }


}