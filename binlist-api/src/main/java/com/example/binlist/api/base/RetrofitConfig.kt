package com.example.binlist.api.base

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * All stuffs required for making HTTP-requests with Retrofit client and
 * for parsing JSON-messages.
 */
@Singleton
class RetrofitConfig @Inject constructor(
    val retrofit: Retrofit
)