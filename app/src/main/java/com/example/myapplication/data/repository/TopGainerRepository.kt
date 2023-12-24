package com.example.myapplication.data.repository
import android.util.Log
import com.example.myapplication.data.remote.StockApi
import com.example.myapplication.domain.model.mostActivelyTraded
import com.example.myapplication.domain.model.topGainers
import com.example.myapplication.domain.model.topLosers

class TopGainerRepository(private val StockApi: StockApi) {

    suspend fun getTopGainers(): List<topGainers>{
        val response = StockApi.getTopTrendEarnings()

        Log.d("ck","here is response on getGeneralNews \n $response")
        Log.d("ck","here is response on getGeneralNews \n ${response.top_gainers}")

        return response.top_gainers
    }

    suspend fun getTopLosers(): List<topLosers>{
        val response = StockApi.getTopTrendEarnings()

        Log.d("ck","here is response on getGeneralNews \n $response")
        Log.d("ck","here is response on getGeneralNews \n ${response.top_losers}")

        return response.top_losers
    }

    suspend fun getMostActivelyTraded(): List<mostActivelyTraded> {
        val response = StockApi.getTopTrendEarnings()

        Log.d("ck", "here is response on getGeneralNews \n $response")
        Log.d("ck", "here is response on getGeneralNews \n ${response.most_actively_traded}")

        return response.most_actively_traded
    }
}
