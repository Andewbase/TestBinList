package com.example.testcft.data

import androidx.lifecycle.LiveData
import com.example.testcft.api.BankApi
import com.example.testcft.api.modelapi.BankCard
import com.example.testcft.data.cache.BankCardInfoDao
import com.example.testcft.data.cache.BankCardInfoEntity
import retrofit2.Response
import javax.inject.Inject

class BankCardRepository @Inject constructor(
    private val api: BankApi,
    private val bankCardInfoDao: BankCardInfoDao
){

    suspend fun getBankCardInformation(number: String): Response<BankCard>{
        return api.getBankCardInformation(number)
    }

    fun getAllBankCardInfo(): LiveData<List<BankCardInfoEntity>> {
      return  bankCardInfoDao.getAllBankCardInfo()
    }

    suspend fun saveCardInfo(bankCardInfoEntity: BankCardInfoEntity){
        bankCardInfoDao.saveBankCardInfoDao(bankCardInfoEntity)
    }

   suspend fun removeOldData(){
        bankCardInfoDao.removeOldData()
    }

}