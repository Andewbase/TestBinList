package com.example.binlist.main.screen.main

import androidx.annotation.StringRes
import com.example.binlist.navigation.MainRouter
import com.example.core.Const.EIGHT_INT
import com.example.core.Const.SEPARATOR

data class MainState(
    val router: MainRouter.Base? = null,
    val loading: Boolean = false,
    val textValue: String = SEPARATOR,
    val maxChar: Int = EIGHT_INT,
    @StringRes val error: Int? = null
)