package com.cryptocurrencyapp.presentation.coin_list.component

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cryptocurrencyapp.common.Constants
import com.cryptocurrencyapp.common.Resource
import com.cryptocurrencyapp.domain.use_case.getcoin.GetCoinUseCase
import com.cryptocurrencyapp.presentation.coin_detail.CoinDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
   savedStateHandle: SavedStateHandle
    ):ViewModel(){

        private val _state = mutableStateOf<CoinDetailState>(CoinDetailState())
        val state:State<CoinDetailState> =_state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let {
            getCoinId(it)
        }
    }

    private fun getCoinId(coinId:String){
        getCoinUseCase(coinId).onEach {
            when(it){
                is Resource.Success ->{
                    _state.value = CoinDetailState(coins = it.data  )
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(error = it.message ?: "An un Expected Error Occured")
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}