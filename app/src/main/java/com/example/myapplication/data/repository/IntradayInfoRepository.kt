package com.example.myapplication.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.myapplication.data.remote.StockApi
import com.example.myapplication.domain.model.IntradayInfo
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import java.io.InputStreamReader
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class IntradayInfoRepository (private val stockApi: StockApi){
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getStockPrice(symbol : String) : List<IntradayInfo> {
        val response = stockApi.getStockGraphInfo(symbol)
        Log.d("ck", "here is response $response")
        Log.d(
            "ck", "here is response in byteStream \n" +
                    " ${response.byteStream()}"
        )
        return unpackPosts(response)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private suspend fun unpackPosts(response: ResponseBody): List<IntradayInfo>{
        val csvReader = CSVReader(InputStreamReader(response.byteStream()))
        val dateTimeFormatterr = DateTimeFormatter.ofPattern("yyyy-MM-dd-dd HH:mm:ss")
        val pattern = "yyyy-MM-dd HH:mm:ss"
        val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
        return withContext(Dispatchers.IO){
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull { line ->
//                    val timeStamp = LocalDateTime.parse(line[0], dateTimeFormatter)
                    val timeStamp = LocalDateTime.parse(line[0], formatter)

                    val close = line[4].toDouble()

                    IntradayInfo(
                        timeStamp = timeStamp,
                        close = close
                    )
                }
                .also {
                    csvReader.close()
                }
        }
    }
}

