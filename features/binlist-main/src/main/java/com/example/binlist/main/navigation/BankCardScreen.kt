package com.example.binlist.main.navigation

import androidx.annotation.StringRes
import com.example.binlist.main.R

enum class BankCardScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    Detail(title = R.string.detail_name)
}