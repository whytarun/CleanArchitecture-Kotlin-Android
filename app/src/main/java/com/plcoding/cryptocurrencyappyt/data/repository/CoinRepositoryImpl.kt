package com.cryptocurrencyapp.data.repository

import com.cryptocurrencyapp.data.remote.dto.CoinApi
import com.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.cryptocurrencyapp.data.remote.dto.CoinDto
import com.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor( private val coinApi: CoinApi):CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return coinApi.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return  coinApi.getCoinById(coinId)
    }
}