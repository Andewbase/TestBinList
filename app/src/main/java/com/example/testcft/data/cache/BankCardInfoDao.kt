package com.example.testcft.data.cache

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BankCardInfoDao {

    @Query("SELECT * FROM bank_info ORDER BY timestamp DESC")
    fun getAllBankCardInfo(): LiveData<List<BankCardInfoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBankCardInfoDao(bankCardInfoEntity: BankCardInfoEntity)

    @Query("DELETE FROM bank_info WHERE number IN(SELECT number FROM bank_info ORDER BY timeStamp DESC LIMIT 1 OFFSET 10)")
    suspend fun removeOldData()

}