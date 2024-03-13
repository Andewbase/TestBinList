package com.example.binlistdata

import com.example.core.Const
import jakarta.inject.Inject
import jakarta.inject.Singleton

class MapperActionsImplementation @Inject constructor(): MapperActions{
    override fun <T> checkValue(value: T?): String {
        return value?.toString() ?: Const.NO_INFORMATION_AVAILABLE
    }

    override fun validUrl(url: String?): String {
        if (url == null) return Const.NO_INFORMATION_AVAILABLE

        return  if (!url.startsWith("https://") || url.startsWith("http://")){
            "http://$url"
        }else{
            url
        }
    }

    override fun checkPhoneNumber(phoneNumber: String?): List<String> {
        return if (phoneNumber == null) return List(Const.TWO_INT){ Const.NO_INFORMATION_AVAILABLE }
        else phoneNumberToList(phoneNumber)
    }

    override fun phoneNumberToList(phoneNumber: String): List<String> {
        val phoneNumbers = phoneNumber.replace(" OR ", ", ")
        val listPhoneNumber = phoneNumbers.split(", ").toList()

        return if (listPhoneNumber.size == Const.ONE_INT){
            val phoneNumberValue1 = listPhoneNumber[Const.ZERO_INT].filter { it.isDigit() }
            listOf(phoneNumberValue1, Const.NO_INFORMATION_AVAILABLE)
        }else{
            val phoneNumberValue1 = listPhoneNumber[Const.ZERO_INT].filter { it.isDigit() }
            val phoneNumberValue2 = listPhoneNumber[Const.ONE_INT].filter { it.isDigit() }
            listOf(phoneNumberValue1, phoneNumberValue2)
        }
    }
}