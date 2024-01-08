package com.example.myapplication.presentation.screens.WatchlistScreen

import com.example.myapplication.domain.model.CompanyListing

data class WatchlistState(
    val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)