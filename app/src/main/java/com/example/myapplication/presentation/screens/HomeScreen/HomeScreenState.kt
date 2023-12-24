package com.example.myapplication.presentation.screens.HomeScreen

import com.example.myapplication.domain.model.mostActivelyTraded
import com.example.myapplication.domain.model.topGainers
import com.example.myapplication.domain.model.topLosers
import kotlinx.coroutines.flow.emptyFlow

data class HomeScreenState(
    val topGainers: List<topGainers>? = emptyList(),
    val topLosers: List<topLosers>? = emptyList(),
    val mostActivelyTraded: List<mostActivelyTraded>? = emptyList(),
    val isRefreshing: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)