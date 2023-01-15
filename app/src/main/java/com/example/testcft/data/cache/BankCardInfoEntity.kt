package com.example.testcft.data.cache

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity(tableName = "bank_info")
@Parcelize
data class BankCardInfoEntity(
    @PrimaryKey
    val number: String,
    val nameBank: String?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val urlBank: String?,
    val length: Int?,
    val countyBank: String?,
    val phoneBank: String?
): Parcelable
