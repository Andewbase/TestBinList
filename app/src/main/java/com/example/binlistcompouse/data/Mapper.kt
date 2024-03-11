package com.example.binlistcompouse.data


import android.annotation.SuppressLint
import com.example.binlistcompouse.Const.FOURTEEN_INT
import com.example.binlistcompouse.Const.FOUR_INT
import com.example.binlistcompouse.Const.NINE_INT
import com.example.binlistcompouse.Const.ONE_INT
import com.example.binlistcompouse.Const.SEPARATOR
import com.example.binlistcompouse.Const.SIXTEEN_INT
import com.example.binlistcompouse.Const.STAR
import com.example.binlistcompouse.Const.ZERO_INT
import com.example.binlistcompouse.Const.ZERO_LONG
import com.example.binlistcompouse.data.cache.entity.BankCardDBO
import com.example.binlistcompouse.data.network.bank.entities.BankCard
import com.example.binlistcompouse.domain.entity.BankCardItemUI
import com.example.binlistcompouse.domain.entity.CardDetailUI
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject


interface Mapper {

    fun bankCardInfoEntityToBankCardItem (bankCardDBO: BankCardDBO): BankCardItemUI

    fun bankCardToBankCardInfoEntity (number: String, bankCard: BankCard): BankCardDBO

    fun bankCardInfoEntityToCardDetail (bankCardDBO: BankCardDBO): CardDetailUI

    fun convertNumber(number: String): String

    class Base @Inject constructor(
        private val mapperActions: MapperActions.Base
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
                id = ZERO_LONG, // SQLite generates identifier automatically if ID = 0
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
                phoneBank1 = phoneNumber[ZERO_INT],
                phoneBank2 = phoneNumber[ONE_INT],
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

            val numberValue = number.padEnd(SIXTEEN_INT, STAR)

            val numberMutableList = numberValue.toMutableList()
            numberMutableList.add(FOUR_INT, WHITESPACE)
            numberMutableList.add(NINE_INT, WHITESPACE)
            numberMutableList.add(FOURTEEN_INT, WHITESPACE)

            return numberMutableList.joinToString(SEPARATOR)
        }

        private companion object Const{
            private const val WHITESPACE = ' '
        }

    }

}

