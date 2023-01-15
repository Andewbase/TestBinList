package com.example.testcft.screen.cache

import androidx.lifecycle.ViewModel
import com.example.testcft.data.BankCardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CacheViewModel @Inject constructor(private val repository: BankCardRepository): ViewModel() {

    val allCardInfo = repository.getAllBankCardInfo()

}