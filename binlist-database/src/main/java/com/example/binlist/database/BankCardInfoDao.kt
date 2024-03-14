package com.example.binlist.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.binlist.database.entity.BankCardDBO
import kotlinx.coroutines.flow.Flow

@Dao
interface BankCardInfoDao {

    @Query("SELECT * FROM bank_info ORDER BY timestamp DESC")
    fun getAllBankCardInfo(): Flow<List<BankCardDBO>>

    @Query("SELECT * FROM bank_info WHERE id = :cardId")
    suspend fun getById(cardId: Long): BankCardDBO

    @Query("SELECT * FROM bank_info WHERE number = :number")
    suspend fun getNumber(number: String): BankCardDBO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBankCardInfoDao(bankCardDBO: BankCardDBO)

    @Query("DELETE FROM bank_info WHERE number IN(SELECT number FROM bank_info ORDER BY timeStamp DESC LIMIT 1 OFFSET 9)")
    suspend fun removeOldData()

}