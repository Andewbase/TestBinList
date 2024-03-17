package com.example.binlist.navigation

import androidx.annotation.StringRes

enum class BankCardScreen (@StringRes val title: Int) {
    Start(title = R.string.main_name),
    Detail(title = R.string.detail_name)
}