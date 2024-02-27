package com.example.binlistcompouse.domain

import com.example.binlistcompouse.data.BankCardRepository
import com.example.binlistcompouse.domain.entity.CardDetail
import javax.inject.Inject

class GetDetailsUseCase @Inject constructor(private val bankCardRepository: BankCardRepository.Base) {


    suspend fun getById(cardId: Long): CardDetail {
        return bankCardRepository.getById(cardId)
    }

    suspend fun getByNumber(number: String): CardDetail {
        return bankCardRepository.getByNumber(number)
    }
}