package com.example.binlistcompouse.domain

import com.example.binlistcompouse.data.BankCardRepository
import com.example.binlistcompouse.domain.entity.CardDetailUI
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(private val bankCardRepository: BankCardRepository.Base) {


    suspend fun getById(cardId: Long): CardDetailUI {
        return bankCardRepository.getById(cardId)
    }

    suspend fun getByNumber(number: String): CardDetailUI {
        return bankCardRepository.getByNumber(number)
    }
}