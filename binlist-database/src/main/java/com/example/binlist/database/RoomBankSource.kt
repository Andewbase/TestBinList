package com.example.binlist.database

import com.example.binlist.database.entity.BankCardDBO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface RoomBankSource {


    fun getAllBankCardInfo(): Flow<List<BankCardDBO>>

    suspend fun getById(cardId: Long): BankCardDBO

    suspend fun getNumber(number: String): BankCardDBO

    suspend fun saveBankCardInfoDao(bankCardDBO: BankCardDBO)

    suspend fun removeOldData()

    @Singleton
    class Base @Inject constructor(
        private val bankCardInfoDao: BankCardInfoDao
        ) : RoomBankSource {

        override fun getAllBankCardInfo(): Flow<List<BankCardDBO>> {
           return bankCardInfoDao.getAllBankCardInfo()
        }

        override suspend fun getById(cardId: Long): BankCardDBO {
            return bankCardInfoDao.getById(cardId)
        }

        override suspend fun getNumber(number: String): BankCardDBO {
            return bankCardInfoDao.getNumber(number)
        }

        override suspend fun saveBankCardInfoDao(bankCardDBO: BankCardDBO)  = wrapSQLiteException{
            bankCardInfoDao.saveBankCardInfoDao(bankCardDBO)
        }

        override suspend fun removeOldData() {
            bankCardInfoDao.removeOldData()
        }

    }
}