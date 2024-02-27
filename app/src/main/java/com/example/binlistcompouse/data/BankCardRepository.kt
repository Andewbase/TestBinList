package com.example.binlistcompouse.data

import com.example.binlistcompouse.data.cache.RoomBankSource
import com.example.binlistcompouse.data.network.RetrofitBankSource
import com.example.binlistcompouse.domain.entity.BankCardItem
import com.example.binlistcompouse.domain.entity.CardDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

interface BankCardRepository {

    suspend fun saveCard(number: String)

    fun getCards(): Flow<List<BankCardItem>>

    suspend fun getById(cardId: Long): CardDetail

    suspend fun getByNumber(number: String): CardDetail

    @Singleton
    class Base @Inject constructor(
        private val retrofitBankSource: RetrofitBankSource.Base,
        private val roomBankSource: RoomBankSource.Base,
        private val mapper: Mapper.Base
        ): BankCardRepository{

        override suspend fun saveCard(number: String){

                val bankCard = retrofitBankSource.getBankInformation(number)
                roomBankSource.removeOldData()

                roomBankSource.saveBankCardInfoDao(mapper.bankCardToBankCardInfoEntity(number, bankCard))
        }

        override fun getCards(): Flow<List<BankCardItem>> {
            val bankCardInfoEntity = roomBankSource.getAllBankCardInfo()
            return bankCardInfoEntity.map { list ->
                list.map {
                    mapper.bankCardInfoEntityToBankCardItem(it)
                }
            }
        }

        override suspend fun getById(cardId: Long): CardDetail {
            val bankCardInfoEntity = roomBankSource.getById(cardId)

            return mapper.bankCardInfoEntityToCardDetail(bankCardInfoEntity)
        }

        override suspend fun getByNumber(number: String): CardDetail {
            val bankCardInfoEntity = roomBankSource.getNumber(mapper.convertNumber(number))

            return mapper.bankCardInfoEntityToCardDetail(bankCardInfoEntity)
        }
    }



}
