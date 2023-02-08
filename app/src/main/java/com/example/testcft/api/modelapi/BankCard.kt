package com.example.testcft.api.modelapi

import android.annotation.SuppressLint
import android.os.Parcelable
import com.example.testcft.data.cache.BankCardInfoEntity
import com.example.testcft.domain.CardDetail
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Parcelize
data class BankCard(
    val bank: Bank,
    val brand: String,
    val country: Country,
    val number: Number,
    val prepaid: Boolean,
    val scheme: String,
    val type: String
): Parcelable{


    @SuppressLint("NewApi")
    fun toBankEntity(numberCard: String): BankCardInfoEntity {
       return BankCardInfoEntity(
            numberFormat(numberCard),
            scheme,
            type,
            brand,
            country.name,
            country.emoji,
            country.currency,
            country.latitude,
            country.longitude,
            bank.name,
            bank.url,
            bank.city,
            bank.phone?.replace(" OR ", ", "),
            timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
        )
    }



    @SuppressLint("NewApi")
    fun toCardDetail(numberCard: String): CardDetail{
       return CardDetail(
            numberFormat(numberCard),
            scheme,
            type,
            brand,
            country.name,
            country.emoji,
            country.currency,
            country.latitude,
            country.longitude,
            bank.name,
            bank.url,
            bank.city,
            bank.phone?.split(" OR ")?.toList(),
            timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
        )
    }

    private fun numberFormat(number: String): String{
        return number.substring(0..3) + " " + number.substring(4..7)
    }

}