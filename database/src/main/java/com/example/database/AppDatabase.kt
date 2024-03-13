package com.example.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.database.entity.BankCardDBO

class AppDatabase internal constructor(private val dataBase: BankCardInfoDataBase){

    val bankCardInfoDao: BankCardInfoDao
        get() = dataBase.bankCardInfoDao()
}

@Database(entities = [BankCardDBO::class], version = 1)
internal abstract class BankCardInfoDataBase: RoomDatabase() {
    abstract fun bankCardInfoDao(): BankCardInfoDao
}

fun BinListDataBase(applicationContext: Context): AppDatabase {
    val db = Room.databaseBuilder(
        applicationContext,
        BankCardInfoDataBase::class.java,
        "bankcard_info_database")
        .build()
    return AppDatabase(db)
}