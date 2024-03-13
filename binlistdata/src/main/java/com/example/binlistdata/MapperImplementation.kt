package com.example.binlistdata

import android.annotation.SuppressLint
import com.example.binlistapi.bank.entities.BankCard
import com.example.binlistdata.entity.BankCardItemUI
import com.example.binlistdata.entity.CardDetailUI
import com.example.database.entity.BankCardDBO
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.text.SimpleDateFormat
import java.util.Date
class MapperImplementation @Inject constructor(
    private val mapperActions: MapperActions
): Mapper{

    override fun bankCardInfoEntityToBankCardItem(bankCardDBO: BankCardDBO): BankCardItemUI {

        return BankCardItemUI(
            id = bankCardDBO.id,
            number = bankCardDBO.number,
            nameBank = bankCardDBO.nameBank,
            scheme = bankCardDBO.scheme,
            countryEmoji = bankCardDBO.countryEmoji
        )
    }

    @SuppressLint("SimpleDateFormat")
    override  fun bankCardToBankCardInfoEntity (number: String, bankCard: BankCard): BankCardDBO {

        val currentDate = Date()
        val simpleDataFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")

        val phoneNumber = mapperActions.checkPhoneNumber(bankCard.bank.phone)

        return BankCardDBO(
            id = com.example.core.Const.ZERO_LONG, // SQLite generates identifier automatically if ID = 0
            number = convertNumber(number),
            scheme = mapperActions.checkValue(bankCard.scheme),
            type = mapperActions.checkValue(bankCard.type),
            brand = mapperActions.checkValue(bankCard.brand),
            countryName = mapperActions.checkValue(bankCard.country.name),
            countryEmoji = mapperActions.checkValue(bankCard.country.emoji),
            currency = mapperActions.checkValue(bankCard.country.currency),
            countryLatitude = mapperActions.checkValue(bankCard.country.latitude),
            countryLongitude = mapperActions.checkValue(bankCard.country.longitude),
            nameBank = mapperActions.checkValue(bankCard.bank.name),
            urlBank = mapperActions.validUrl(bankCard.bank.url),
            cityBank = mapperActions.checkValue(bankCard.bank.city),
            phoneBank1 = phoneNumber[com.example.core.Const.ZERO_INT],
            phoneBank2 = phoneNumber[com.example.core.Const.ONE_INT],
            timestamp = simpleDataFormat.format(currentDate)
        )
    }

    override fun bankCardInfoEntityToCardDetail(bankCardDBO: BankCardDBO): CardDetailUI {

        return CardDetailUI(
            number = bankCardDBO.number,
            scheme = bankCardDBO.scheme,
            type = bankCardDBO.type,
            brand = bankCardDBO.brand,
            countryName = bankCardDBO.countryName,
            countryEmoji = bankCardDBO.countryEmoji,
            currency = bankCardDBO.currency,
            countryLatitude = bankCardDBO.countryLatitude,
            countryLongitude = bankCardDBO.countryLongitude,
            nameBank = bankCardDBO.nameBank,
            urlBank = bankCardDBO.urlBank,
            cityBank = bankCardDBO.cityBank,
            phoneBank1 = bankCardDBO.phoneBank1,
            phoneBank2 = bankCardDBO.phoneBank2
        )

    }
    override fun convertNumber(number: String): String{

        val numberValue = number.padEnd(
            com.example.core.Const.SIXTEEN_INT,
            com.example.core.Const.STAR
        )

        val numberMutableList = numberValue.toMutableList()
        numberMutableList.add(com.example.core.Const.FOUR_INT, WHITESPACE)
        numberMutableList.add(com.example.core.Const.NINE_INT, WHITESPACE)
        numberMutableList.add(com.example.core.Const.FOURTEEN_INT, WHITESPACE)

        return numberMutableList.joinToString(com.example.core.Const.SEPARATOR)
    }

    private companion object Const{
        private const val WHITESPACE = ' '
    }
}