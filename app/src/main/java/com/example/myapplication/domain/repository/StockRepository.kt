package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.CompanyBalanceSheet
import com.example.myapplication.domain.model.CompanyCashFlow
import com.example.myapplication.domain.model.CompanyInfo
import com.example.myapplication.util.Resource
import kotlinx.coroutines.flow.Flow
import com.example.myapplication.domain.model.CompanyListing
import com.example.myapplication.domain.model.CompanyIncomeStatement
import com.example.myapplication.domain.model.IntradayInfo

interface StockRepository {
    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>

    suspend fun getCompanyInfo(
        symbol: String
    ): Resource<CompanyInfo>

    suspend fun getCompanyBalanceSheet(
        symbol: String
    ): Resource<List<CompanyBalanceSheet>>

    suspend fun getCompanyIncomeStatement(
        symbol: String
    ): Resource<List<CompanyIncomeStatement>>

    suspend fun getCompanyCashFlow(
        symbol: String
    ): Resource<List<CompanyCashFlow>>

//    suspend fun getIntradayInfo(
//        symbol: String
//    ): Resource<List<IntradayInfo>>
}