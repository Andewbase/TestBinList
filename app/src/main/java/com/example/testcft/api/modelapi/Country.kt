package com.example.testcft.api.modelapi

import java.io.Serializable

data class Country(
    val name: String,
    val emoji: String,
    val currency: String,
    val latitude: Double,
    val longitude: Double
): Serializable