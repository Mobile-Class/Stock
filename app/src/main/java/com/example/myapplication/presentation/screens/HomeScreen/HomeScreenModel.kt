package com.example.myapplication.presentation.screens.HomeScreen

// Model
data class TrendingEarningsItem(val companyName: String, val shortSlogan: String, val todayDate: String)
data class TrendingStocksItem(val title: String, val percentageChange: String, val symbols: String)
data class MostActivesItem(val name: String, val chart: String, val value: String, val change: String, val post: String)
