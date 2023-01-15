package com.example.testcft.api.modelapi

import android.os.Parcelable
import com.example.testcft.data.cache.BankCardInfoEntity
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class BankCard(
    val bank: Bank,
    val brand: String,
    val country: Country,
    val number: Number,
    val prepaid: Boolean,
    val scheme: String,
    val type: String
): Serializable, Parcelable{

    fun toBankEntity() = BankCardInfoEntity(
        bank.name,
        scheme,
        type,
        brand,
        bank.url,
        number.length,
        country.name,
        bank.phone
    )

}