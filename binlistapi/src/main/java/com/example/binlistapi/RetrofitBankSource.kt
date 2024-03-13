package com.example.binlistapi

import com.example.binlistapi.bank.entities.BankCard

interface RetrofitBankSource {
    suspend fun getBankInformation(number: String): BankCard
}