package com.example.binlistcompouse.data.cache

import com.example.binlistcompouse.data.cache.entity.BankCardInfoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

interface RoomBankSource {


    fun getAllBankCardInfo(): Flow<List<BankCardInfoEntity>>

    suspend fun getById(cardId: Long): BankCardInfoEntity

    suspend fun getNumber(number: String): BankCardInfoEntity

    suspend fun saveBankCardInfoDao(bankCardInfoEntity: BankCardInfoEntity)

    suspend fun removeOldData()

    @Singleton
    class Base @Inject constructor(
        private val bankCardInfoDao: BankCardInfoDao
        ) : RoomBankSource{

        override fun getAllBankCardInfo(): Flow<List<BankCardInfoEntity>> {
           return bankCardInfoDao.getAllBankCardInfo()
        }

        override suspend fun getById(cardId: Long): BankCardInfoEntity {
            return bankCardInfoDao.getById(cardId)
        }

        override suspend fun getNumber(number: String): BankCardInfoEntity {
            return bankCardInfoDao.getNumber(number)
        }

        override suspend fun saveBankCardInfoDao(bankCardInfoEntity: BankCardInfoEntity)  = wrapSQLiteException{
            bankCardInfoDao.saveBankCardInfoDao(bankCardInfoEntity)
        }

        override suspend fun removeOldData() {
            bankCardInfoDao.removeOldData()
        }

    }
}