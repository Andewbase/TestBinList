package com.example.binlist_home.screen.main

import androidx.annotation.StringRes
import androidx.navigation.NavController

sealed interface MainEvent {

    data class UpdateTextValue(val textValue: String): MainEvent

    data class ClearTextValue(val textValue: String = ""): MainEvent

    data class ShowNumberCard(val value: String, val navigate: NavController?): MainEvent

    data class Navigate(val navigate: NavController): MainEvent

    data class Loading(val loading: Boolean): MainEvent

    data class Error(@StringRes val error: Int?): MainEvent

}