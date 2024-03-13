package com.example.binlistapi.base

import com.example.core.AppException
import com.example.core.ConnectionException
import com.example.core.LimitException
import com.example.core.ParseBackendResponseException
import com.example.core.ThereIsNoBankCard
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonEncodingException
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException

/**
 * Base class for all OkHttp sources.
 */
open class BaseRetrofitSource(
    retrofit: Retrofit
): RetrofitSourceActions {

    val retrofit: Retrofit = retrofit

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