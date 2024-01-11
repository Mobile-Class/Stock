package com.example.myapplication.presentation.screens.NewFeedScreen

import com.example.myapplication.domain.model.News

data class NewFeedState (
    val news: List<News> = emptyList(),
    val isRefreshing: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val selectedCategory: String = ""
//    val searchQuery: String = ""
)