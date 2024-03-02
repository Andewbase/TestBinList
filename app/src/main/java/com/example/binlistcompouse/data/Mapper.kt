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
import com.example.binlistcompouse.data.cache.entity.BankCardInfoEntity
import com.example.binlistcompouse.data.network.bank.entities.BankCard
import com.example.binlistcompouse.domain.entity.BankCardItem
import com.example.binlistcompouse.domain.entity.CardDetail
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject


interface Mapper {

    fun bankCardInfoEntityToBankCardItem (bankCardInfoEntity: BankCardInfoEntity): BankCardItem

    fun bankCardToBankCardInfoEntity (number: String, bankCard: BankCard): BankCardInfoEntity

    fun bankCardInfoEntityToCardDetail (bankCardInfoEntity: BankCardInfoEntity): CardDetail

    fun convertNumber(number: String): String

    class Base @Inject constructor(
        private val mapperActions: MapperActions.Base
    ): Mapper{

        override fun bankCardInfoEntityToBankCardItem(bankCardInfoEntity: BankCardInfoEntity): BankCardItem {

            return BankCardItem(
                id = bankCardInfoEntity.id,
                number = bankCardInfoEntity.number,
                nameBank = bankCardInfoEntity.nameBank,
                scheme = bankCardInfoEntity.scheme,
                countryEmoji = bankCardInfoEntity.countryEmoji
            )
        }

        @SuppressLint("SimpleDateFormat")
        override  fun bankCardToBankCardInfoEntity (number: String, bankCard: BankCard): BankCardInfoEntity {

            val currentDate = Date()
            val simpleDataFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")

            val phoneNumber = mapperActions.checkPhoneNumber(bankCard.bank.phone)

            return BankCardInfoEntity(
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

        override fun bankCardInfoEntityToCardDetail(bankCardInfoEntity: BankCardInfoEntity): CardDetail {

            return CardDetail(
                number = bankCardInfoEntity.number,
                scheme = bankCardInfoEntity.scheme,
                type = bankCardInfoEntity.type,
                brand = bankCardInfoEntity.brand,
                countryName = bankCardInfoEntity.countryName,
                countryEmoji = bankCardInfoEntity.countryEmoji,
                currency = bankCardInfoEntity.currency,
                countryLatitude = bankCardInfoEntity.countryLatitude,
                countryLongitude = bankCardInfoEntity.countryLongitude,
                nameBank = bankCardInfoEntity.nameBank,
                urlBank = bankCardInfoEntity.urlBank,
                cityBank = bankCardInfoEntity.cityBank,
                phoneBank1 = bankCardInfoEntity.phoneBank1,
                phoneBank2 = bankCardInfoEntity.phoneBank2
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

