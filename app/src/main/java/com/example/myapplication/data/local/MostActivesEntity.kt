package com.example.myapplication.data.local
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MostActivesEntity(
    val ticker: String,
    val price: String,
    val changeAmount: String,
    val changePercentage: String,
    val volume: String,
    @PrimaryKey val id: Int? = null
)