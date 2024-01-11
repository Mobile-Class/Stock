package com.example.myapplication.presentation.screens.DetailScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.remote.StockApi
import com.example.myapplication.data.repository.IntradayInfoRepository
import com.example.myapplication.data.repository.TopGainerRepository
import com.example.myapplication.domain.model.IntradayInfo
import com.example.myapplication.domain.model.topGainers
import com.example.myapplication.domain.repository.StockRepository
import com.example.myapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyInfoViewModel @Inject constructor(
    private val repository: StockRepository,
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val alphaVantageAPIForJSON = StockApi.createURLForCSV()
    private val intradayStockRepository = IntradayInfoRepository(alphaVantageAPIForJSON)
    var state by mutableStateOf(CompanyInfoState())
    init {
        viewModelScope.launch {
            val symbol = savedStateHandle.get<String>("symbol") ?: return@launch
            state = state.copy(isLoading = true)
            val companyInfoResult = async { repository.getCompanyInfo(symbol) }
            val balanceSheetResult = async { repository.getCompanyBalanceSheet(symbol) }
            val incomeStatementResult = async { repository.getCompanyIncomeStatement(symbol) }
            val cashFlowResult = async { repository.getCompanyCashFlow(symbol) }
            val intradayInfoResult = intradayStockRepository.getStockPrice(symbol)

            //Company overview data
            when(val result = companyInfoResult.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        company = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = result.message,
                        company = null
                    )
                }
                else -> Unit
            }

            // Balancce Sheet Data
            when(val result = balanceSheetResult.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        balanceSheet = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = result.message,
                        company = null
                    )
                }
                else -> Unit
            }

            // Income Statement Data
            when(val result = incomeStatementResult.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        incomeStatement = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = result.message,
                        company = null
                    )
                }
                else -> Unit
            }

            // Cash Flow Data
            when(val result = cashFlowResult.await()) {
                is Resource.Success -> {
                    state = state.copy(
                        cashFlow = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        error = result.message,
                        company = null
                    )
                }
                else -> Unit
            }

            // Chart Data
//            when(val result = intradayInfoResult.await()) {
//                is Resource.Success -> {
//                    state = state.copy(
//                        stockInfos = result.data ?: emptyList(),
//                        isLoading = false,
//                        error = null
//                    )
//                }
//                is Resource.Error -> {
//                    state = state.copy(
//                        isLoading = false,
//                        error = result.message,
//                        company = null
//                    )
//                }
//                else -> Unit
//            }
            intradayInfoHandleSuccess(intradayInfoResult)
//            Log.d("stockPrice","here is stock details =========== \n $IntradayInfo")
//            Log.d("testchart", "here is stock details value =========== \n ${stockInfos.value}")
        }
    }
    private fun intradayInfoHandleSuccess(data: List<IntradayInfo>) {
        state = state.copy(
            stockInfos = data,
            isLoading = false,
            error = null
        )

    }
}