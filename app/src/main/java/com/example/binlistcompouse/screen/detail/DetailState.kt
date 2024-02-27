package com.example.binlistcompouse.screen.detail

import com.example.binlistcompouse.Const.SEPARATOR
import com.example.binlistcompouse.domain.entity.CardDetail

data class DetailState(
    val cardDetail: CardDetail = CardDetail(
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
