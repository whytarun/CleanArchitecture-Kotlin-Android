package com.cryptocurrencyapp.presentation.coin_detail

import com.cryptocurrencyapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading :Boolean = false,
    val coins :CoinDetail? =null,
    val error: String =""
)
