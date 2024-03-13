package com.example.binlistapi

import com.example.binlistapi.bank.BankApi
import com.example.binlistapi.bank.entities.BankCard
import com.example.binlistapi.base.BaseRetrofitSource
import jakarta.inject.Inject
import jakarta.inject.Singleton
import retrofit2.Retrofit

@Singleton
class RetrofitBankSourceImplementation @Inject constructor (retrofit: Retrofit) : BaseRetrofitSource(retrofit), RetrofitBankSource{

    private val bankApi = retrofit.create(BankApi::class.java)

    override suspend fun getBankInformation(number: String): BankCard = wrapRetrofitExceptions {
        bankApi.getBankCardInformation(number)
    }
}