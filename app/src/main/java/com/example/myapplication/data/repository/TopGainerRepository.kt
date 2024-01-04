package com.example.myapplication.data.repository
import android.util.Log
import com.example.myapplication.data.remote.StockApi
import com.example.myapplication.domain.model.mostActivelyTraded
import com.example.myapplication.domain.model.topGainers
import com.example.myapplication.domain.model.topLosers

class TopGainerRepository(private val stockApi: StockApi) {

    suspend fun getTopGainers(): List<topGainers> {
        val response = stockApi.getTopTrendEarnings()

        Log.d("ck", "here is response on getTopGainers \n $response")
        Log.d("ck", "here is response on getTopGainers \n ${response.top_gainers}")

        val topGainersList = response.top_gainers
        if (topGainersList.isEmpty()) {
            Log.d("ck", "Top Gainers list is empty")
        }

        return topGainersList
    }

    suspend fun getTopLosers(): List<topLosers> {
        val response = stockApi.getTopTrendEarnings()

        Log.d("ck", "here is response on getTopLosers \n $response")
        Log.d("ck", "here is response on getTopLosers \n ${response.top_losers}")

        val topLosersList = response.top_losers
        if (topLosersList.isEmpty()) {
            Log.d("ck", "Top Losers list is empty")
        }

        return topLosersList
    }

    suspend fun getMostActivelyTraded(): List<mostActivelyTraded> {
        val response = stockApi.getTopTrendEarnings()

        Log.d("ck", "here is response on getMostActivelyTraded \n $response")
        Log.d("ck", "here is response on getMostActivelyTraded \n ${response.most_actively_traded}")

        val mostActivelyTradedList = response.most_actively_traded
        if (mostActivelyTradedList.isEmpty()) {
            Log.d("ck", "Most Actively Traded list is empty")
        }

        return mostActivelyTradedList
    }
}
