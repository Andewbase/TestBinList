package com.example.binlistcompouse.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.binlistcompouse.data.cache.entity.BankCardDBO

@Database(entities = [BankCardDBO::class], version = 1)
abstract class BankCardInfoDataBase: RoomDatabase() {
    abstract fun getBankCardInfoDao(): BankCardInfoDao
}