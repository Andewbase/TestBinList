package com.example.testcft.screen.main

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import com.example.testcft.api.modelapi.Bank
import com.example.testcft.api.modelapi.BankCard
import com.example.testcft.api.modelapi.Country
import com.example.testcft.data.BankCardRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import retrofit2.Response

class MainViewModelTest {

    private val repository = mock<BankCardRepository>()
    private val responce = mock<Response<BankCard>>()

    @AfterEach
    fun afterEach(){
        Mockito.reset(repository)
        Mockito.reset(responce)
        ArchTaskExecutor.getInstance().setDelegate(null)
    }

    @BeforeEach
    fun beforEach(){
        ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor(){
            override fun executeOnDiskIO(runnable: Runnable) {
                runnable.run()
            }

            override fun postToMainThread(runnable: Runnable) {
                runnable.run()
            }

            override fun isMainThread(): Boolean {
                return true
            }

        })
    }

    @Test
    fun shouldgetBankCardInformation() = runBlocking{

        val testNumber = "44556677"
        val bank = Bank("Sber", "http//Sber", "+799999", "Moscow")
        val country = Country("Russia", "\uD83C\uDDF7\uD83C\uDDFA", "RUB", 59.7692794, 88.322266)
        val numberBank = com.example.testcft.api.modelapi.Number(44, false)
        val bankCard = BankCard(bank, "John Doe", country, numberBank, true, "sceme", "type")

        Mockito.`when`(responce.isSuccessful).thenReturn(true)
        Mockito.`when`(responce.body()).thenReturn(bankCard)
        Mockito.`when`(repository.getBankCardInformation(number = any())).thenReturn(Response.success(bankCard))

        val viewModel = MainViewModel(repository)

        viewModel.getBankCardInformation(testNumber)

        val expected = "44556677"
        val actual = viewModel.bankCardLiveData.value

        Mockito.verify(repository).getBankCardInformation(testNumber)
        Mockito.verify(repository).saveCardInfo(bankCard.toBankEntity(testNumber))
        Mockito.verify(repository).removeOldData()
        Mockito.verify(repository, Mockito.times(1)).getBankCardInformation(number = testNumber)
        Assertions.assertEquals(expected, actual)
    }

}