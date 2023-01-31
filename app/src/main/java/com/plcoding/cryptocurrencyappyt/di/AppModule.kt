package com.cryptocurrencyapp.di

import com.cryptocurrencyapp.common.Constants
import com.cryptocurrencyapp.data.remote.dto.CoinApi
import com.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.cryptocurrencyapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCoinApi():CoinApi{
        return Retrofit.Builder().apply {
            baseUrl(Constants.BASE_URL)
            addConverterFactory(GsonConverterFactory.create())
        }.build().create(CoinApi::class.java)
    }

    @Singleton
    @Provides
    fun ProvideCoinRepository(api:CoinApi) :CoinRepository{
        return CoinRepositoryImpl(api)
    }
}