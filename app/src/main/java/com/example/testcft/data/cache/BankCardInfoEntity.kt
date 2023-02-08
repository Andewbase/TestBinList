package com.example.testcft.data.cache

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.testcft.domain.CardDetail
import kotlinx.parcelize.Parcelize


@Entity(tableName = "bank_info")
@Parcelize
data class BankCardInfoEntity(
    @PrimaryKey
    val number: String,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val countryName: String?,
    val countryEmoji: String?,
    val currency: String?,
    val countryLatitude: Double?,
    val countryLongitude: Double?,
    val nameBank: String?,
    val urlBank: String?,
    val cityBank: String?,
    val phoneBank: String?,
    val timestamp: String
): Parcelable{

    fun toCardDetail() : CardDetail{
        return CardDetail(
            number = number,
            scheme = scheme,
            type = type,
            brand = brand,
            countryName = countryName,
            countryEmoji = countryEmoji,
            countryLatitude = countryLatitude,
            countryLongitude = countryLongitude,
            currency = currency,
            nameBank = nameBank,
            urlBank = urlBank,
            cityBank = cityBank,
            phoneBank = phoneBank?.split(", ")?.toList(),
            timestamp = timestamp
        )
    }


}
