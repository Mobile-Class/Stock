package com.example.myapplication.data.remote

import android.text.SpannableString
import com.example.myapplication.data.remote.dto.CompanyBalanceSheetDto
import com.example.myapplication.data.remote.dto.CompanyCashFlowDto
import com.example.myapplication.data.remote.dto.CompanyIncomeStatementDto
import com.example.myapplication.data.remote.dto.CompanyInfoDto
import com.example.myapplication.domain.model.CompanyIncomeStatement
import com.example.myapplication.domain.model.News
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

    @GET("query?function=TOP_GAINERS_LOSERS")
    suspend fun getTopTrendEarnings(
        @Query("apikey") apiKey: String = API_KEY
    ): TopGainersResponse

    class TopGainersResponse(
        val top_gainers: List<topGainers>,
        val top_losers: List<topLosers>,
        val most_actively_traded: List<mostActivelyTraded>

    )

    @GET("query?function=NEWS_SENTIMENT")
    suspend fun getGeneralNews (
        @Query("apikey") apikey: String = API_KEY
    ) : ListingFeed

    @GET("query?function=NEWS_SENTIMENT")
    suspend fun getNewsForStock (
        @Query("tickers") tickers : String,
        @Query("limit") limit : String = "10",
        @Query("apikey") apikey: String = API_KEY
    ) : ListingFeed

    @GET("query?function=NEWS_SENTIMENT")
    suspend fun getNewsWithCategory (
        @Query("topics") topics : String,
        @Query("apikey") apikey: String = API_KEY
    ) : ListingFeed

    class ListingFeed(
        val feed: List<News>
    )


    companion object {
        const val API_KEY = "LSV36CEZ47SULMPM"

//        const val API_KEY = "ZZZ0H0IO4EG9QQX6"

//        const val API_KEY = "demo"

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

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): CompanyInfoDto

    @GET("query?function=BALANCE_SHEET")
    suspend fun getCompanyBalanceSheet(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): CompanyBalanceSheetDto

    @GET("query?function=INCOME_STATEMENT")
    suspend fun getCompanyIncomeStatement(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): CompanyIncomeStatementDto

    @GET("query?function=CASH_FLOW")
    suspend fun getCompanyCashFlow(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): CompanyCashFlowDto

    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv")
    suspend fun getIntradayInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody
}