package com.example.myapplication.presentation.screens.HomeScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


// ViewModel
class HomeViewModel : ViewModel() {

    private val _trendingEarningsData = MutableLiveData<List<TrendingEarningsItem>>()
    val trendingEarningsData: LiveData<List<TrendingEarningsItem>> get() = _trendingEarningsData

    private val _trendingStocksData = MutableLiveData<List<TrendingStocksItem>>()
    val trendingStocksData: LiveData<List<TrendingStocksItem>> get() = _trendingStocksData

    private val _mostActivesData = MutableLiveData<List<MostActivesItem>>()
    val mostActivesData: LiveData<List<MostActivesItem>> get() = _mostActivesData

    init {
        // Initialize and fetch data here
        fetchData()
    }

    private fun fetchData() {
        // Simulate data fetching or make a network request using viewModelScope
        viewModelScope.launch {
            val trendingEarningsList = generateTrendingEarnings(4)
            val trendingStocksList = generateTrendingStocks(2)
            val mostActivesList = generateMostActives(2)

            _trendingEarningsData.value = trendingEarningsList
            _trendingStocksData.value = trendingStocksList
            _mostActivesData.value = mostActivesList
        }
    }

    private fun generateTrendingEarnings(count: Int): List<TrendingEarningsItem> {
        return List(count) { index ->
            TrendingEarningsItem("Company $index", "Short Slogan", "11/11/2023")
        }
    }

    private fun generateTrendingStocks(count: Int): List<TrendingStocksItem> {
        return List(count) { index ->
            TrendingStocksItem("Stock $index", "+${(0..10).random()}%", "${(10..50).random()} Symbols")
        }
    }

    private fun generateMostActives(count: Int): List<MostActivesItem> {
        return List(count) { index ->
            MostActivesItem("Item $index", "Chart", "${(100..200).random()}.76", "-${(1..5).random()}%", "Post: -${(1..5).random()}%")
        }
    }
}

