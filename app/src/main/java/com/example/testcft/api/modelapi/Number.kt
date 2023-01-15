package com.example.testcft.api.modelapi

import java.io.Serializable

data class Number(
    val length: Int,
    val luhn: Boolean
): Serializable