package com.cryptocurrencyapp.domain.use_case.getcoins

import com.cryptocurrencyapp.common.Resource
import com.cryptocurrencyapp.data.remote.dto.toCoin
import com.cryptocurrencyapp.domain.model.Coin
import com.cryptocurrencyapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinRepository) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
                emit(Resource.Loading())
                val coins = repository.getCoins().map { it.toCoin() }
                emit(Resource.Success(coins))
        } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "An unExpected error occured"))
        } catch (e: IOException) {
                emit(Resource.Error("couldn't reach server. check your internet connection "))
        }
    }
}