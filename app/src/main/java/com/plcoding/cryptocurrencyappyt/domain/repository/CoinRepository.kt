package com.cryptocurrencyapp.domain.repository

import com.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.cryptocurrencyapp.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins():List<CoinDto>
    suspend fun getCoinById(coinId:String):CoinDetailDto
}