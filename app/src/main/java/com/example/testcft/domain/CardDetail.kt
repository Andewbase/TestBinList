package com.example.testcft.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardDetail(
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
    val phoneBank: List<String>?,
    val timestamp: String
): Parcelable