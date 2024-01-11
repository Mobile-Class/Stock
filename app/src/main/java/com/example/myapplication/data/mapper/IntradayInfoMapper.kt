package com.example.myapplication.data.mapper

//import android.os.Build
//import androidx.annotation.RequiresApi
//import com.example.myapplication.data.remote.dto.IntradayInfoDto
//import com.example.myapplication.domain.model.IntradayInfo
//import java.time.LocalDateTime
//import java.time.format.DateTimeFormatter
//import java.util.*

//@RequiresApi(Build.VERSION_CODES.O) // TODO fix that
//fun IntradayInfoDto.toIntradayInfo(): IntradayInfo {
//    val pattern = "yyyy-MM-dd HH:mm:ss"
//    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
//    val localDateTime = LocalDateTime.parse(timestamp, formatter)
//    return IntradayInfo(
//        date = localDateTime,
//        close = close
//    )
//}
//
//@RequiresApi(Build.VERSION_CODES.O)
//fun IntradayInfoDto.toIntradayInfo(): List<IntradayInfo> {
//    val pattern = "yyyy-MM-dd HH:mm:ss"
//    val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
//
//    return timeSeries.entries.mapNotNull { entry ->
//        val timestamp = entry.key.timestamp
//        val localDateTime = LocalDateTime.parse(timestamp, formatter)
//        val close = entry.value.close.toDoubleOrNull()
//
//        // Only include entries where close can be converted to Double
//        if (close != null) {
//            IntradayInfo(
//                date = localDateTime,
//                close = close
//            )
//        } else {
//            null
//        }
//    }
//}