package com.example.binlist.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "bank_info",
    indices = [
        Index("number", unique = true)
    ]
)
data class BankCardDBO(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")val id: Long,
    @ColumnInfo(name = "number", collate = ColumnInfo.NOCASE) val number: String,
    @ColumnInfo(name = "scheme") val scheme: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "brand") val brand: String,
    @ColumnInfo(name = "countryName") val countryName: String,
    @ColumnInfo(name = "countryEmoji") val countryEmoji: String,
    @ColumnInfo(name = "currency") val currency: String,
    @ColumnInfo(name = "countryLatitude") val countryLatitude: String,
    @ColumnInfo(name = "countryLongitude") val countryLongitude: String,
    @ColumnInfo(name = "nameBank") val nameBank: String,
    @ColumnInfo(name = "urlBank") val urlBank: String,
    @ColumnInfo(name = "cityBank") val cityBank: String,
    @ColumnInfo(name = "phoneBank1") val phoneBank1: String,
    @ColumnInfo(name = "phoneBank2") val phoneBank2: String,
    @ColumnInfo(name = "timestamp") val timestamp: String
)