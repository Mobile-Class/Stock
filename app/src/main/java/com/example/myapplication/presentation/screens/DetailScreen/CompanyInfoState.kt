package com.example.myapplication.presentation.screens.DetailScreen

import com.example.myapplication.domain.model.CompanyBalanceSheet
import com.example.myapplication.domain.model.CompanyCashFlow
import com.example.myapplication.domain.model.CompanyInfo
import com.example.myapplication.domain.model.CompanyIncomeStatement
import com.example.myapplication.domain.model.IntradayInfo

data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val cashFlow : List <CompanyCashFlow> = emptyList(),
    val balanceSheet: List <CompanyBalanceSheet> = emptyList(),
    val incomeStatement: List <CompanyIncomeStatement> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
