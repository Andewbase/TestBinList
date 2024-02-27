package com.example.binlistcompouse.data.network.base

import com.example.binlistcompouse.data.network.AppException
import com.example.binlistcompouse.data.network.ConnectionException
import com.example.binlistcompouse.data.network.LimitException
import com.example.binlistcompouse.data.network.ParseBackendResponseException
import com.example.binlistcompouse.data.network.ThereIsNoBankCard
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonEncodingException
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException

/**
 * Base class for all OkHttp sources.
 */
open class BaseRetrofitSource(
    retrofitConfig: RetrofitConfig
): RetrofitSourceActions {

    val retrofit: Retrofit = retrofitConfig.retrofit

    /**
     * Map network and parse exceptions into in-app exceptions.
     * @throws ParseBackendResponseException
     * @throws ConnectionException
     */
    suspend fun <T> wrapRetrofitExceptions(block: suspend () -> T): T {
        return try {
            block()
        } catch (e: AppException) {
            throw e
            // moshi
        } catch (e: JsonDataException) {
            throw ParseBackendResponseException(e)
        } catch (e: JsonEncodingException) {
            throw ParseBackendResponseException(e)
            // retrofit
        } catch (e: HttpException) {
            handleHttpError(e)
            // mostly retrofit
        } catch (e: IOException) {
            throw ConnectionException(e)
        }
    }

    override fun<T> handleHttpError(e: HttpException): T {
        when (e.code()) {
            400 -> throw ThereIsNoBankCard(e)
            404 -> throw ThereIsNoBankCard(e)
            429 -> throw LimitException(e)
            else -> throw Exception(e)
        }
    }

}

interface RetrofitSourceActions{
    fun<T> handleHttpError(e: HttpException): T
}