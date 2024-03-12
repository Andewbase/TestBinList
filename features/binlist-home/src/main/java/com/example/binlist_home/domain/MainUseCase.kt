package com.example.binlist_home.domain

import com.example.binlistdata.BankCardRepository
import com.example.binlistdata.entity.BankCardItemUI
import com.example.core.Const.SEVEN_INT
import com.example.core.Const.SIX_INT
import com.example.core.DeleteOrAddOneCharacterException
import com.example.core.EmptyException
import com.example.core.NotTheAppropriateSizeException
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