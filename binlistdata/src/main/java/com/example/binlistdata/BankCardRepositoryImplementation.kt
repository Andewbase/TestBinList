package com.example.binlistdata

import com.example.binlistapi.RetrofitBankSource
import com.example.binlistdata.entity.BankCardItemUI
import com.example.binlistdata.entity.CardDetailUI
import com.example.database.RoomBankSource
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BankCardRepositoryImplementation @Inject constructor(
    private val retrofitBankSource: RetrofitBankSource,
    private val roomBankSource: RoomBankSource.Base,
    private val mapper: Mapper
): BankCardRepository {

    override suspend fun saveCard(number: String){

        val bankCard = retrofitBankSource.getBankInformation(number)
        roomBankSource.removeOldData()

        roomBankSource.saveBankCardInfoDao(mapper.bankCardToBankCardInfoEntity(number, bankCard))
    }

    override fun getCards(): Flow<List<BankCardItemUI>> {
        val bankCardInfoEntity = roomBankSource.getAllBankCardInfo()
        return bankCardInfoEntity.map { list ->
            list.map {
                mapper.bankCardInfoEntityToBankCardItem(it)
            }
        }
    }

    override suspend fun getById(cardId: Long): CardDetailUI {
        val bankCardInfoEntity = roomBankSource.getById(cardId)

        return mapper.bankCardInfoEntityToCardDetail(bankCardInfoEntity)
    }

    override suspend fun getByNumber(number: String): CardDetailUI {
        val bankCardInfoEntity = roomBankSource.getNumber(mapper.convertNumber(number))

        return mapper.bankCardInfoEntityToCardDetail(bankCardInfoEntity)
    }
}