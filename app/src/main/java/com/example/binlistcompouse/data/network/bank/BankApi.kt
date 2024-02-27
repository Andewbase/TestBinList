package com.example.binlistcompouse.data.network.bank

import com.example.binlistcompouse.data.network.bank.entities.BankCard
import retrofit2.http.GET
import retrofit2.http.Path

    interface BankApi {

        @GET("/{number}")
        suspend fun getBankCardInformation(
            @Path("number") number: String
        ): BankCard

    }

