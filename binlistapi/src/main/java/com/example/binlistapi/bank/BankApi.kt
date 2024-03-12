package com.example.binlistapi.bank

import com.example.binlistapi.bank.entities.BankCard
import retrofit2.http.GET
import retrofit2.http.Path

    interface BankApi {

        @GET("/{number}")
        suspend fun getBankCardInformation(
            @Path("number") number: String
        ): BankCard

    }

