package com.example.myapplication.data.remote.dto

import com.squareup.moshi.Json

data class IntradayInfoDto(
    val timestamp: String,
    val close: Double
)
//data class IntradayInfoDto(
//    @Json(name = "Meta Data") val metaData: MetaDataDto,
//    @Json(name = "Time Series (60min)") val timeSeries: Map<Timestamp, HourlyDataDto>
//)
//
//data class MetaDataDto(
//    @Json(name = "1. Information") val information: String,
//    @Json(name = "2. Symbol") val symbol: String,
//    @Json(name = "3. Last Refreshed") val lastRefreshed: String,
//    @Json(name = "4. Interval") val interval: String,
//    @Json(name = "5. Output Size") val outputSize: String,
//    @Json(name = "6. Time Zone") val timeZone: String
//)
//
//data class HourlyDataDto(
//    @Json(name = "1. open") val open: String,
//    @Json(name = "2. high") val high: String,
//    @Json(name = "3. low") val low: String,
//    @Json(name = "4. close") val close: String,
//    @Json(name = "5. volume") val volume: String
//)
//
//data class Timestamp(
//    @Json(name = "timestamp") val timestamp: String
//)
