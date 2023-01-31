package com.plcoding.cryptocurrencyappyt.presentation.coin_list.component

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.cryptocurrencyappyt.common.Resource
import com.plcoding.cryptocurrencyappyt.domain.model.Coin
import com.plcoding.cryptocurrencyappyt.domain.use_case.getcoin.GetCoinUseCase
import com.plcoding.cryptocurrencyappyt.domain.use_case.getcoins.GetCoinsUseCase
import com.plcoding.cryptocurrencyappyt.presentation.coin_list.CoinListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase):ViewModel(){

        private val _state = mutableStateOf<CoinListState>(CoinListState())
        val state:State<CoinListState> =_state

    init {
        getCoins()
    }

    private fun getCoins(){
        getCoinsUseCase().onEach {
            when(it){
                is Resource.Success ->{
                    _state.value = CoinListState(coins = it.data ?: emptyList() )
                }
                is Resource.Error -> {
                    _state.value = CoinListState(error = it.message ?: "An un Expected Error Occured")
                }
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}