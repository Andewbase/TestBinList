package com.example.binlistcompouse.data.network

import com.example.binlistcompouse.data.network.bank.BankApi
import com.example.binlistcompouse.data.network.bank.entities.BankCard
import com.example.binlistcompouse.data.network.base.BaseRetrofitSource
import com.example.binlistcompouse.data.network.base.RetrofitConfig
import javax.inject.Inject
import javax.inject.Singleton

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