package com.example.myapplication.presentation.screens.HomeScreen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.StockApi
import com.example.myapplication.data.repository.TopGainerRepository
import com.example.myapplication.domain.model.mostActivelyTraded
import com.example.myapplication.domain.model.topGainers
import com.example.myapplication.domain.model.topLosers
import com.example.myapplication.domain.model.userWatchList
import com.example.myapplication.presentation.screens.SearchScreen.CompanyListingsEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeViewModel () : ViewModel() {

    private val alphaVantageAPIForJSON = StockApi.createURLForJSON()
    private val stockNewsRepository = TopGainerRepository(alphaVantageAPIForJSON)

    private val _state = mutableStateOf(HomeScreenState())
    val state: State<HomeScreenState> get() = _state

//   _state private val _refreshCompletedCompleted = mutableStateOf(false)
//    val refreshCompleted: State<Boolean> get() = _refreshCompleted

    init {
        getHomeScreenData()
    }

    fun onEvent(event: HomeScreenEvent) {
        when(event) {
            is HomeScreenEvent.Refresh -> {
                getHomeScreenData()
//                _refreshCompleted.value = true
                Log.d("ck","Refresh Data completed")
            }

            else -> {}
        }
    }


    private fun getHomeScreenData() {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(isLoading = true)
                val topGainersList = stockNewsRepository.getTopGainers()
                val topLosersList = stockNewsRepository.getTopLosers()
                val mostActivelyTradedList = stockNewsRepository.getMostActivelyTraded()
                val userWatchList =
                topGainersHandleSuccess(topGainersList)
                topLosersHandleSuccess(topLosersList)
                topActivelyTradedHandleSuccess(mostActivelyTradedList)

            }  catch (e: Exception) {
                handleError(e.message ?: "Unknown error")
            } finally {
                handleLoading(false)
//                _refreshCompleted.value = false
//                Log.d("ck", "Refresh Data Error")
            }
        }
    }

    private fun topGainersHandleSuccess(data: List<topGainers>?) {
        _state.value = _state.value.copy(
            topGainers = data,
            isLoading = false,
            error = null
        )
//        onRefreshCompleted()
    }

    private fun topLosersHandleSuccess(data: List<topLosers>?) {
        _state.value = _state.value.copy(
            topLosers = data,
            isLoading = false,
            error = null
        )
//        onRefreshCompleted()
    }

    private fun topActivelyTradedHandleSuccess(data: List<mostActivelyTraded>?) {
        _state.value = _state.value.copy(
            mostActivelyTraded = data,
            isLoading = false,
            error = null
        )
//        onRefreshCompleted()
    }



    private fun handleError(errorMessage: String) {
        _state.value = _state.value.copy(
            isLoading = false,
            error = errorMessage
        )
//        onRefreshCompleted()
    }

    private fun handleLoading(isLoading: Boolean) {
        _state.value = _state.value.copy(isLoading = isLoading)
    }

    private fun onRefreshCompleted() {
//        _refreshCompleted.value = true
        Log.d("ck", "Refresh Data completed")
    }



}
