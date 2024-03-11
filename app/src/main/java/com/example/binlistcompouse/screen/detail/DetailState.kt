package com.example.binlistcompouse.screen.detail

import com.example.binlistcompouse.Const.SEPARATOR
import com.example.binlistcompouse.domain.entity.CardDetailUI

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
