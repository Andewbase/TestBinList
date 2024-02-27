package com.example.binlistcompouse.data.cache

import android.database.sqlite.SQLiteException
import com.example.binlistcompouse.data.network.AppException
import com.example.binlistcompouse.data.network.StorageException

/**
 * Converts any [SQLiteException] into in-app [StorageException]
 */
suspend fun <T> wrapSQLiteException(block: suspend () -> T): T {
   return try {
        block()
    } catch (e: AppException) {
        throw StorageException(e)
    }
}