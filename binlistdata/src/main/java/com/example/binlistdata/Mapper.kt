package com.example.binlistdata

import com.example.binlistapi.bank.entities.BankCard
import com.example.binlistdata.entity.BankCardItemUI
import com.example.binlistdata.entity.CardDetailUI
import com.example.database.entity.BankCardDBO

interface Mapper {

    fun bankCardInfoEntityToBankCardItem (bankCardDBO: BankCardDBO): BankCardItemUI

    fun bankCardToBankCardInfoEntity (number: String, bankCard: BankCard): BankCardDBO

    fun bankCardInfoEntityToCardDetail (bankCardDBO: BankCardDBO): CardDetailUI

    fun convertNumber(number: String): String

}

