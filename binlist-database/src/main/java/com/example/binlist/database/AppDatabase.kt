package com.example.binlist.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.binlist.database.entity.BankCardDBO

@Database(entities = [BankCardDBO::class], version = 1)
abstract class BankCardInfoDataBase: RoomDatabase() {
    abstract fun getBankCardInfoDao(): BankCardInfoDao
}