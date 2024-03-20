package com.example.binlist.main.screen.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binlist.main.domain.GetDetailsUseCase
import com.example.core.Const.NO_INFORMATION_AVAILABLE
import com.example.core.Const.SEPARATOR
import com.example.core.Const.ZERO_LONG
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
                detailState = detailState.copy(cardDetailUI = cardDetail)
            }

            if (number != SEPARATOR){
                val cardDetail = getDetailsUseCase.getByNumber(number)
                detailState = detailState.copy(cardDetailUI = cardDetail)
            }

            with(detailState.cardDetailUI){
                if (countryName == NO_INFORMATION_AVAILABLE) detailState = detailState.copy(countryNameEnabled = false)
                if (urlBank == NO_INFORMATION_AVAILABLE) detailState = detailState.copy(urlBankEnabled = false)
                if (phoneBank1 == NO_INFORMATION_AVAILABLE ) detailState = detailState.copy(phoneBankEnabled = false)
                if (phoneBank2 == NO_INFORMATION_AVAILABLE) detailState = detailState.copy(phoneBankTwoEnabled = false)
            }
        }
    }

}