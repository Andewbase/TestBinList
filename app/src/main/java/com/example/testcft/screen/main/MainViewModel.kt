package com.example.testcft.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testcft.api.modelapi.BankCard
import com.example.testcft.data.BankCardRepository
import com.example.testcft.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: BankCardRepository
): ViewModel() {

    private val _bankCardLiveData: MutableLiveData<Resource<BankCard>> = MutableLiveData()
    val bankCardLiveData: LiveData<Resource<BankCard>> = _bankCardLiveData

    val allCardInfo = repository.getAllBankCardInfo()

    fun getBankCardInformation(number: String){
        viewModelScope.launch {
            _bankCardLiveData.postValue(Resource.Loading())
            val response = repository.getBankCardInformation(number)
            if (response.isSuccessful){
                response.body().let { bank ->
                    _bankCardLiveData.postValue(Resource.Success(bank))
                     repository.saveCardInfo(bank!!.toBankEntity(number))
                    repository.removeOldData()
                }
            }else{
                _bankCardLiveData.postValue(Resource.Error(message = response.message()))
            }
        }
    }

}