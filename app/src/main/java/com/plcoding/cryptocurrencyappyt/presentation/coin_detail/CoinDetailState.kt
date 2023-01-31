package com.plcoding.cryptocurrencyappyt.presentation.coin_detail

import com.plcoding.cryptocurrencyappyt.domain.model.Coin

data class CoinDetailState(
    val isLoading :Boolean = false,
    val coins :List<Coin> = emptyList(),
    val error: String =""
)
