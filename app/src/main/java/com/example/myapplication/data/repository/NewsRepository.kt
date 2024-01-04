package com.example.myapplication.data.repository
import android.util.Log
import com.example.myapplication.data.remote.StockApi
import com.example.myapplication.domain.model.News

class NewsRepository (private val stockApi: StockApi) {
    suspend fun getGeneralNews(): List<News>{
        val response = stockApi.getGeneralNews()

        Log.d("ck","here is response on getGeneralNews \n ${response.feed}")
        val generalNewsList = response.feed
        if (generalNewsList.isEmpty()) {
            Log.d("ck", "General News List list is empty")
        }
        return response.feed
    }

    suspend fun getStockNews(symbol: String): List<News>{
        val response = stockApi.getNewsForStock(symbol)

        Log.d("ck","here is response on getStockNews \n ${response.feed}")
        val stockNewsList = response.feed
        if (stockNewsList.isEmpty()) {
            Log.d("ck", "Stock News List list is empty")
        }
        return response.feed
    }

    suspend fun getNewsWithCategory(category: String): List<News>{
        val response = stockApi.getNewsWithCategory(category)

        Log.d("getNewsWithCategory","here is response on getNewsWithCategory \n ${response.feed}")

        val newsWithCategoryList = response.feed
        if (newsWithCategoryList.isEmpty()) {
            Log.d("ck", "feed list is empty")
        }
        return response.feed
    }
}



