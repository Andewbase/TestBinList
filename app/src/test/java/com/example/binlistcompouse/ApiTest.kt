package com.example.binlistcompouse

import com.example.binlistcompouse.Const.BASE_URL
import com.example.binlistcompouse.data.network.bank.BankApi
import com.example.binlistcompouse.data.network.bank.entities.Bank
import com.example.binlistcompouse.data.network.bank.entities.BankCard
import com.example.binlistcompouse.data.network.bank.entities.Country
import com.example.binlistcompouse.data.network.bank.entities.Number
import com.squareup.moshi.Moshi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiTest {

    @Test
    fun test () = runBlocking {
        val moshi = Moshi.Builder().build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

        val api: BankApi = retrofit.create(BankApi::class.java)

        val actual = api.getBankCardInformation(TEST_NUMBER)
        val expected = bankCard
        assertEquals(expected, actual)
    }

    private val bankCard = BankCard(
        bank = Bank(
            name =  "Jyske Bank A/S",
            url = null,
            phone = null,
            city = null
        ),
        brand = "Visa Classic",
        country = Country(
            name = "Denmark",
            emoji = "🇩🇰",
            currency = "DKK",
            latitude = 56.0,
            longitude = 10.0
        ),
        number = Number(
            length = 0,
            luhn = false,
        ),
        prepaid = false,
        scheme = "visa",
        type = "debit"
    )

    private companion object{
        const val TEST_NUMBER = "45717360"
    }


}