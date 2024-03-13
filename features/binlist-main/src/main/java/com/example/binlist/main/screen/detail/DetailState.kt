package com.example.binlist.main.screen.detail

import com.example.binlistdata.entity.CardDetailUI
import com.example.core.Const.SEPARATOR

data class DetailState(
    val cardDetailUI: CardDetailUI = CardDetailUI(
        SEPARATOR,
        SEPARATOR,
        SEPARATOR,
        SEPARATOR,
        SEPARATOR,
        SEPARATOR,
        SEPARATOR,
        SEPARATOR,
        SEPARATOR,
        SEPARATOR,
        SEPARATOR,
        SEPARATOR,
        SEPARATOR,
        SEPARATOR
    ),
    val countryNameEnabled: Boolean = true,
    val urlBankEnabled: Boolean = true,
    val phoneBankEnabled: Boolean = true,
    val phoneBankTwoEnabled: Boolean = true
)
