package com.example.testcft.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BankCardInfoEntity::class], version = 1)
abstract class BankCardInfoDataBase: RoomDatabase() {
    abstract fun getBankCardInfoDao(): BankCardInfoDao
}