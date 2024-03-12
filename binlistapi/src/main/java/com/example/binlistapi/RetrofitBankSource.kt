package com.example.binlistapi

import com.example.binlistapi.bank.BankApi
import com.example.binlistapi.bank.entities.BankCard
import com.example.binlistapi.base.BaseRetrofitSource
import com.example.binlistapi.base.RetrofitConfig
import jakarta.inject.Inject
import jakarta.inject.Singleton

interface RetrofitBankSource {

    suspend fun getBankInformation(number: String): BankCard


    @Singleton
    class Base @Inject constructor (config: RetrofitConfig) : BaseRetrofitSource(config), RetrofitBankSource{

        private val bankApi = retrofit.create(BankApi::class.java)

        override suspend fun getBankInformation(number: String): BankCard = wrapRetrofitExceptions {
            bankApi.getBankCardInformation(number)
        }

    }


}