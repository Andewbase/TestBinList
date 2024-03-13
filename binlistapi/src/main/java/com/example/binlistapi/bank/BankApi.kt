package com.example.binlistapi.bank

import com.example.binlistapi.bank.entities.BankCard
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

interface BankApi {

        @GET("/{number}")
        suspend fun getBankCardInformation(
            @Path("number") number: String
        ): BankCard

}

fun BankApi(
    baseUrl: String
): BankApi{
    return retrofit(baseUrl, okHttpClient(), moshi()).create()
}

private fun retrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient,
    moshi: Moshi
): Retrofit{
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()
}

private fun okHttpClient(): OkHttpClient{
    return OkHttpClient.Builder()
        .addInterceptor(createLoggingInterceptor())
        .build()
}

private fun moshi(): Moshi{
    return Moshi.Builder().build()
}

private fun createLoggingInterceptor(): Interceptor {
    return HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
}

