package com.example.binlistcompouse

import com.example.binlistcompouse.Const.NO_INFORMATION_AVAILABLE
import com.example.binlistcompouse.data.Mapper
import com.example.binlistcompouse.data.cache.entity.BankCardInfoEntity
import com.example.binlistcompouse.data.network.bank.entities.Bank
import com.example.binlistcompouse.data.network.bank.entities.BankCard
import com.example.binlistcompouse.data.network.bank.entities.Country
import com.example.binlistcompouse.data.network.bank.entities.Number
import com.example.binlistcompouse.domain.entity.BankCardItem
import com.example.binlistcompouse.domain.entity.CardDetail
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Date
import java.text.SimpleDateFormat

class MapperTest {

    private val mapper  = Mapper.Base()

    @Test
    fun test_bankCardInfoEntityToBankCardItem(){
        val result = mapper.bankCardInfoEntityToBankCardItem(bankCardInfoEntity = bankCardInfoEntity)
        val expected = bankCardItem

        assertEquals(expected, result)
    }

    @Test
    fun test_bankCardToBankCardInfoEntity(){
        val result = mapper.bankCardToBankCardInfoEntity(NUMBER, bankCard)
        val expected = bankCardInfoEntity

        assertEquals(result, expected)
    }

    @Test
    fun test_bankCardInfoEntityToCardDetail(){
        val result = mapper.bankCardInfoEntityToCardDetail(bankCardInfoEntity = bankCardInfoEntity)
        val expected = cardDetail

        assertEquals(result, expected)
    }

    @Test
    fun test_convertNumber(){
        val result = mapper.convertNumber(NUMBER)
        val expected = NUMBER_EXPECTED

        assertEquals(result, expected)

    }

    @Test
    fun test_checkValue(){
        val result = mapper.checkValue(null)
        val expected = NO_INFORMATION_AVAILABLE

        assertEquals(result, expected)
    }

    @Test
    fun test_validUrl(){
        val result = mapper.validUrl(NO_INFORMATION_AVAILABLE)
        val expected = URL_EXPECTED

        assertEquals(result, expected)
    }

    @Test
    fun test_checkPhoneNumber(){
        val result = mapper.checkPhoneNumber(null)
        val expected = listOf(NO_INFORMATION_AVAILABLE, NO_INFORMATION_AVAILABLE)

        assertEquals(result, expected)
    }

    @Test
    fun test_phoneNumberToList(){
        val result = mapper.phoneNumberToList(PHONE_NUMBER)
        val expected = listOf(PHONE_NUMBER_EXPECTED, PHONE_NUMBER_EXPECTED)

        assertEquals(result, expected)
    }

    private val currentDate = Date()
    private val simpleDataFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")

    private val bankCardInfoEntity = BankCardInfoEntity(
        id = ID,
        number = NUMBER_EXPECTED,
        scheme = NO_INFORMATION_AVAILABLE,
        type = NO_INFORMATION_AVAILABLE,
        brand = NO_INFORMATION_AVAILABLE,
        countryName = NO_INFORMATION_AVAILABLE,
        countryEmoji = NO_INFORMATION_AVAILABLE,
        currency = NO_INFORMATION_AVAILABLE,
        countryLatitude = COUNTRY_EXPECTED,
        countryLongitude = COUNTRY_EXPECTED,
        nameBank = NO_INFORMATION_AVAILABLE,
        urlBank = URL_EXPECTED,
        cityBank = NO_INFORMATION_AVAILABLE,
        phoneBank1 = NO_INFORMATION_AVAILABLE,
        phoneBank2 = NO_INFORMATION_AVAILABLE,
        timestamp = simpleDataFormat.format(currentDate)
    )

    private val bankCardItem = BankCardItem(
        id = ID,
        number = NUMBER_EXPECTED,
        nameBank = NO_INFORMATION_AVAILABLE,
        scheme = NO_INFORMATION_AVAILABLE,
        countryEmoji = NO_INFORMATION_AVAILABLE
    )

    private val cardDetail = CardDetail(
        number = NUMBER_EXPECTED,
        scheme = NO_INFORMATION_AVAILABLE,
        type = NO_INFORMATION_AVAILABLE,
        brand = NO_INFORMATION_AVAILABLE,
        countryName = NO_INFORMATION_AVAILABLE,
        countryEmoji = NO_INFORMATION_AVAILABLE,
        currency = NO_INFORMATION_AVAILABLE,
        countryLatitude = COUNTRY_EXPECTED,
        countryLongitude = COUNTRY_EXPECTED,
        nameBank = NO_INFORMATION_AVAILABLE,
        urlBank = URL_EXPECTED,
        cityBank = NO_INFORMATION_AVAILABLE,
        phoneBank1 = NO_INFORMATION_AVAILABLE,
        phoneBank2 = NO_INFORMATION_AVAILABLE
    )

    private val bankCard = BankCard(
        bank =  Bank(
            name = NO_INFORMATION_AVAILABLE,
            url = NO_INFORMATION_AVAILABLE,
            phone = null,
            city = NO_INFORMATION_AVAILABLE
        ),
        brand = NO_INFORMATION_AVAILABLE,
        country =  Country(
            name =  NO_INFORMATION_AVAILABLE,
            emoji = NO_INFORMATION_AVAILABLE,
            currency = NO_INFORMATION_AVAILABLE,
            latitude = COUNTRY,
            longitude = COUNTRY
        ),
        number =  Number(
            length = 0,
            luhn = false
        ),
        prepaid = false,
        scheme = NO_INFORMATION_AVAILABLE,
        type =  NO_INFORMATION_AVAILABLE
    )

    private companion object{
        const val ID = 0L
        const val NUMBER = "54657689"
        const val NUMBER_EXPECTED = "5465 7689 **** ****"
        const val URL_EXPECTED = "http://-"
        const val COUNTRY = 0.0
        const val COUNTRY_EXPECTED = "0.0"
        const val PHONE_NUMBER = "44 OR 44"
        const val PHONE_NUMBER_EXPECTED = "44"
    }

}