package com.example.testcft.api.modelapi

import java.io.Serializable


data class Bank(
    val name: String,
    val url: String,
    val phone: String,
    val city: String
): Serializable