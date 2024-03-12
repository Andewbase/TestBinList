package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.entity.BankCardDBO

@Database(entities = [BankCardDBO::class], version = 1)
abstract class BankCardInfoDataBase: RoomDatabase() {
    abstract fun getBankCardInfoDao(): BankCardInfoDao
}