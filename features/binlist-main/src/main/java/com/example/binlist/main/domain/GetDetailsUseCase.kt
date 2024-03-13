package com.example.binlist.main.domain

import com.example.binlistdata.BankCardRepository
import com.example.binlistdata.entity.CardDetailUI
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(private val bankCardRepository: BankCardRepository) {


    suspend fun getById(cardId: Long): CardDetailUI {
        return bankCardRepository.getById(cardId)
    }

    suspend fun getByNumber(number: String): CardDetailUI {
        return bankCardRepository.getByNumber(number)
    }
}