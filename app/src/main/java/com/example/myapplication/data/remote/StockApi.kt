package com.example.myapplication.data.remote

import android.text.SpannableString
import com.example.myapplication.domain.model.mostActivelyTraded
import com.example.myapplication.domain.model.topGainers
import com.example.myapplication.domain.model.topLosers
import com.google.android.gms.common.api.ApiException
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.lang.reflect.Type

interface StockApi {

    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody

    @GET("/query?function=TOP_GAINERS_LOSERS")
    suspend fun getTopTrendEarnings(
        @Query("apikey") apiKey: String = API_KEY
    ): TopGainersResponse

    class TopGainersResponse(
        val top_gainers: List<topGainers>,
        val top_losers: List<topLosers>,
        val most_actively_traded: List<mostActivelyTraded>

    )

    @GET("/query?function=TOP_TREND_STOCKS")
    suspend fun getTopTrendStocks(
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody

    @GET("/query?function=MOST_ACTIVES")
    suspend fun getMostActives(
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody


    companion object {
        const val API_KEY = "DCXKD7J70IWCLMET"

        const val BASE_URL = "https://alphavantage.co"

        var httpUrl = HttpUrl.Builder()
            .scheme("https")
            .host("www.alphavantage.co")
            .build()

        //create unique url for csv file
        fun createURLForCSV(): StockApi = createURLForCSV(httpUrl)

        private fun createURLForCSV(httpUrl: HttpUrl): StockApi {
            val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BASIC
                })
                .build()
            return Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(client)
                .build()
                .create(StockApi::class.java)
        }


        //create unique url for JSON
        fun createURLForJSON(): StockApi = createURLForJSON(httpUrl)
        private fun createURLForJSON(httpUrl: HttpUrl): StockApi {
            val client = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    // Enable basic HTTP logging to help with debugging.
                    this.level = HttpLoggingInterceptor.Level.BASIC
                })
                .build()
            return Retrofit.Builder()
                .baseUrl(httpUrl)
                .client(client)
                .addConverterFactory(buildGsonConverterFactory())
                .build()
                .create(StockApi::class.java)
        }

        // Tell Gson to use our SpannableString deserializer
        private fun buildGsonConverterFactory(): GsonConverterFactory {
            val gsonBuilder = GsonBuilder().registerTypeAdapter(
                SpannableString::class.java, SpannableDeserializer()
            )
            return GsonConverterFactory.create(gsonBuilder.create())
        }

        // This class allows Retrofit to parse items in our model of type
        // SpannableString.  Note, given the amount of "work" we do to
        // enable this behavior, one can argue that Retrofit is a bit...."simple."
        class SpannableDeserializer : JsonDeserializer<SpannableString> {
            // @Throws(JsonParseException::class)
            override fun deserialize(
                json: JsonElement,
                typeOfT: Type,
                context: JsonDeserializationContext
            ): SpannableString {
                return SpannableString(json.asString)
            }
        }
    }
}