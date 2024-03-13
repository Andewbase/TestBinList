package com.example.binlistdata

interface MapperActions{
    fun <T>checkValue(value: T?): String

    fun validUrl(url: String?): String

    fun checkPhoneNumber(phoneNumber: String?): List<String>

    fun phoneNumberToList(phoneNumber: String): List<String>
}