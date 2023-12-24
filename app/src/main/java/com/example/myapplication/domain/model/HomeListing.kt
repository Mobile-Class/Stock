package com.example.myapplication.domain.model
import com.google.gson.annotations.SerializedName

data class topGainers(
    val ticker: String,
    val price: String,
    @SerializedName("change_amount")
    val changeAmount: String,
    @SerializedName("change_percentage")
    val changePercentage: String,
    val volume: String
)

data class topLosers(
    val ticker: String,
    val price: String,
    @SerializedName("change_amount")
    val changeAmount: String,
    @SerializedName("change_percentage")
    val changePercentage: String,
    val volume: String
)
data class mostActivelyTraded(
    val ticker: String,
    val price: String,
    @SerializedName("change_amount")
    val changeAmount: String,
    @SerializedName("change_percentage")
    val changePercentage: String,
    val volume: String
)