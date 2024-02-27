package com.example.binlistcompouse.data.cache.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bank_info")
data class BankCardInfoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo("number") val number: String,
    @ColumnInfo("scheme") val scheme: String,
    @ColumnInfo("type") val type: String,
    @ColumnInfo("brand") val brand: String,
    @ColumnInfo("countryName") val countryName: String,
    @ColumnInfo("countryEmoji") val countryEmoji: String,
    @ColumnInfo("currency") val currency: String,
    @ColumnInfo("countryLatitude") val countryLatitude: String,
    @ColumnInfo("countryLongitude") val countryLongitude: String,
    @ColumnInfo("nameBank") val nameBank: String,
    @ColumnInfo("urlBank") val urlBank: String,
    @ColumnInfo("cityBank") val cityBank: String,
    @ColumnInfo("phoneBank1") val phoneBank1: String,
    @ColumnInfo("phoneBank2") val phoneBank2: String,
    @ColumnInfo("timestamp") val timestamp: String
)