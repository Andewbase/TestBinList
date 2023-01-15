package com.example.testcft.data.cache

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BankCardInfoDao {

    @Query("SELECT * FROM bank_info")
    fun getAllBankCardInfo(): LiveData<List<BankCardInfoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveBankCardInfoDao(bankCardInfoEntity: BankCardInfoEntity)

}