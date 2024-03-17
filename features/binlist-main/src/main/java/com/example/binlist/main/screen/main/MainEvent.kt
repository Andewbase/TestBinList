package com.example.binlist.main.screen.main

import androidx.annotation.StringRes
import com.example.binlist.navigation.MainRouter

sealed interface MainEvent {

    data class UpdateTextValue(val textValue: String): MainEvent

    data class ClearTextValue(val textValue: String = ""): MainEvent

    data class ShowNumberCard(val value: String, val navigate: MainRouter.Base?): MainEvent

    data class Navigate(val mainRouter: MainRouter.Base): MainEvent

    data class Loading(val loading: Boolean): MainEvent

    data class Error(@StringRes val error: Int?): MainEvent

}