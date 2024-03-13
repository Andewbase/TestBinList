package com.example.binlistdata

import com.example.binlistdata.entity.BankCardItemUI
import com.example.binlistdata.entity.CardDetailUI
import kotlinx.coroutines.flow.Flow

interface BankCardRepository {

    suspend fun saveCard(number: String)

    fun getCards(): Flow<List<BankCardItemUI>>

    suspend fun getById(cardId: Long): CardDetailUI

    suspend fun getByNumber(number: String): CardDetailUI

}
