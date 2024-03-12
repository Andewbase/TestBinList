package com.example.database

import android.database.sqlite.SQLiteException
import com.example.core.AppException
import com.example.core.StorageException

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