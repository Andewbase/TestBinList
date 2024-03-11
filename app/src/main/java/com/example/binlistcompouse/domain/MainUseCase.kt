package com.example.binlistcompouse.domain

import com.example.binlistcompouse.Const.SEVEN_INT
import com.example.binlistcompouse.Const.SIX_INT
import com.example.binlistcompouse.data.BankCardRepository
import com.example.binlistcompouse.data.network.DeleteOrAddOneCharacterException
import com.example.binlistcompouse.data.network.EmptyException
import com.example.binlistcompouse.data.network.NotTheAppropriateSizeException
import com.example.binlistcompouse.domain.entity.BankCardItemUI
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainUseCase @Inject constructor(private val bankCardRepository: BankCardRepository.Base) {

    suspend fun saveCard(number: String){

        if (number.isBlank()) throw EmptyException()
        if (number.length < SIX_INT) throw DeleteOrAddOneCharacterException()
        if (number.length == SEVEN_INT) throw NotTheAppropriateSizeException()

        bankCardRepository.saveCard(number)
    }

    fun getCards(): Flow<List<BankCardItemUI>>{

        return bankCardRepository.getCards()

    }

}