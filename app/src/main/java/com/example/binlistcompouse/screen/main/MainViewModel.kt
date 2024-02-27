package com.example.binlistcompouse.screen.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.binlistcompouse.R
import com.example.binlistcompouse.data.network.ConnectionException
import com.example.binlistcompouse.data.network.DeleteOrAddOneCharacterException
import com.example.binlistcompouse.data.network.EmptyException
import com.example.binlistcompouse.data.network.LimitException
import com.example.binlistcompouse.data.network.NotTheAppropriateSizeException
import com.example.binlistcompouse.data.network.StorageException
import com.example.binlistcompouse.data.network.ThereIsNoBankCard
import com.example.binlistcompouse.domain.MainUseCase
import com.example.binlistcompouse.screen.BankCardScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainUseCase: MainUseCase,
) : ViewModel(), MainActions {

    val getFullBankCars = mainUseCase.getCards()

    var mainState: MainState by mutableStateOf(MainState())
    private set

    fun send (mainEvent: MainEvent){
        when (mainEvent){
            is MainEvent.UpdateTextValue -> mainState = mainState.copy(textValue = mainEvent.textValue)
            is MainEvent.ClearTextValue -> mainState = mainState.copy(textValue = mainEvent.textValue)
            is MainEvent.Navigate -> mainState = mainState.copy(navigate = mainEvent.navigate)
            is MainEvent.ShowNumberCard -> showNumberCard(mainState.textValue, navController = mainState.navigate)
            is MainEvent.Loading -> mainState = mainState.copy(loading = mainEvent.loading)
            is MainEvent.Error -> mainState = mainState.copy(error = mainEvent.error)
        }
    }

   override fun showNumberCard(numberCard: String, navController: NavController?){
        viewModelScope.launch {

            send(MainEvent.Loading(loading = true))

            try {

                mainUseCase.saveCard(numberCard)

                send(MainEvent.Error(error = null))
                send(MainEvent.ClearTextValue())
                navController!!.navigate(route = BankCardScreen.Detail.name + "?number=${numberCard}")

            }catch (e: EmptyException){
                send(MainEvent.Error(error = R.string.empty_the_input_field))
            }catch (e: DeleteOrAddOneCharacterException ){
                send(MainEvent.Error(error = R.string.not_the_appropriate))
            } catch (e: NotTheAppropriateSizeException){
                send(MainEvent.Error(error = R.string.delete_or_add_one_character))
            } catch (e: LimitException){
                send(MainEvent.Error(error = R.string.limit_error))
                send(MainEvent.ClearTextValue())
            }catch (e: ThereIsNoBankCard){
                send(MainEvent.Error(error = R.string.thereisnobankcard_error))
                send(MainEvent.ClearTextValue())
            } catch (e: ConnectionException){
                send(MainEvent.Error(error = R.string.connection_error))
            } catch (e: StorageException){
                send(MainEvent.Error(error = R.string.storage_error))
            } catch (e: Exception){
                send(MainEvent.Error(error = R.string.backend_error))
            }
            finally {
                send(MainEvent.Loading(loading = false))
            }
        }
    }

}

interface MainActions{
    fun showNumberCard(numberCard: String, navController: NavController?)
}