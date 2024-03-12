package com.example.binlist_home.screen.main

import androidx.annotation.StringRes
import androidx.navigation.NavController
import com.example.core.Const.EIGHT_INT
import com.example.core.Const.SEPARATOR

data class MainState(
    val navigate: NavController? = null,
    val loading: Boolean = false,
    val textValue: String = SEPARATOR,
    val maxChar: Int = EIGHT_INT,
    @StringRes val error: Int? = null
)