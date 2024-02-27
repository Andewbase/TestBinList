package com.example.binlistcompouse.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.binlistcompouse.data.cache.entity.BankCardInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BankCardInfoDao {

    @Query("SELECT * FROM bank_info ORDER BY timestamp DESC")
    fun getAllBankCardInfo(): Flow<List<BankCardInfoEntity>>

    @Query("SELECT * FROM bank_info WHERE id = :cardId")
    suspend fun getById(cardId: Long): BankCardInfoEntity

    @Query("SELECT * FROM bank_info WHERE number = :number")
    suspend fun getNumber(number: String): BankCardInfoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBankCardInfoDao(bankCardInfoEntity: BankCardInfoEntity)

    @Query("DELETE FROM bank_info WHERE number IN(SELECT number FROM bank_info ORDER BY timeStamp DESC LIMIT 1 OFFSET 9)")
    suspend fun removeOldData()

}