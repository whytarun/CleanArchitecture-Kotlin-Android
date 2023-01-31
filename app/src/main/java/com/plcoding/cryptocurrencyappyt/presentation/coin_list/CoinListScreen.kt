package com.plcoding.cryptocurrencyappyt.presentation.coin_list.component

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.plcoding.cryptocurrencyapp.presentation.coin_list.component.CoinListViewModel


@Composable
fun CoinListScreen(
        navController :NavController,
        viewModel: CoinListViewModel = hiltViewModel()
){
    val state =viewModel.state.value
}