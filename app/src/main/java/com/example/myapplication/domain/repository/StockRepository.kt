package com.example.myapplication.domain.repository

import com.example.myapplication.util.Resource
import kotlinx.coroutines.flow.Flow
import com.example.myapplication.domain.model.CompanyListing

interface StockRepository {
    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>
}