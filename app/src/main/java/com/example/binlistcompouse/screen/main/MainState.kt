package com.example.binlistcompouse.screen.main

import androidx.annotation.StringRes
import androidx.navigation.NavController
import com.example.binlistcompouse.Const.EIGHT_INT
import com.example.binlistcompouse.Const.SEPARATOR

data class MainState(
    val navigate: NavController? = null,
    val loading: Boolean = false,
    val textValue: String = SEPARATOR,
    val maxChar: Int = EIGHT_INT,
    @StringRes val error: Int? = null
)