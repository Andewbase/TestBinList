package com.example.testcft.api

import com.example.testcft.api.modelapi.BankCard
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BankApi {

    companion object{
        const val BASE_URL = "https://lookup.binlist.net"
    }

    @GET("/{number}")
    suspend fun getBankCardInformation(
        @Path("number") number: String
    ): Response<BankCard>
}