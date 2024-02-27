package com.example.binlistcompouse.screen.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binlistcompouse.Const.NO_INFORMATION_AVAILABLE
import com.example.binlistcompouse.Const.SEPARATOR
import com.example.binlistcompouse.Const.ZERO_LONG
import com.example.binlistcompouse.domain.GetDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailsUseCase: GetDetailsUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    var detailState: DetailState by mutableStateOf(DetailState())
        private set

    init {
        viewModelScope.launch {
            val id: Long = checkNotNull(savedStateHandle["id"])
            val number: String = checkNotNull(savedStateHandle["number"])

            if (id != ZERO_LONG){
                val cardDetail = getDetailsUseCase.getById(id)
                detailState = detailState.copy(cardDetail = cardDetail)
            }

            if (number != SEPARATOR){
                val cardDetail = getDetailsUseCase.getByNumber(number)
                detailState = detailState.copy(cardDetail = cardDetail)
            }

            if (detailState.cardDetail.countryName == NO_INFORMATION_AVAILABLE){
                detailState = detailState.copy(countryNameEnabled = false)
            }
            if (detailState.cardDetail.urlBank == NO_INFORMATION_AVAILABLE){
                detailState = detailState.copy(urlBankEnabled = false)
            }
            if (detailState.cardDetail.phoneBank1 == NO_INFORMATION_AVAILABLE){
                detailState = detailState.copy(phoneBankEnabled = false)
            }
            if (detailState.cardDetail.phoneBank2 == NO_INFORMATION_AVAILABLE){
                detailState = detailState.copy(phoneBankTwoEnabled = false)
            }
        }
    }

}