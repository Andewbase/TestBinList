package com.example.binlistcompouse.data.network.bank.entities

data class BankCard(
    val bank: Bank,
    val brand: String,
    val country: Country,
    val number: Number,
    val prepaid: Boolean,
    val scheme: String,
    val type: String
)
