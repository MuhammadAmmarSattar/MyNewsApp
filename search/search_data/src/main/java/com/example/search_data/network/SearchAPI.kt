package com.example.search_data.network

import com.example.core_ui.Constant
import com.example.search_data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface SearchAPI {
    //https://newsapi.org/v2/everything?q=apple&from=2023-03-09&to=2023-03-09&sortBy=popularity&apiKey=f6cdb4941ed04d25add50a9da3384517
    @GET("everything")
    suspend fun getSearchArticles(
        @Query("q") country: String= "apple",
        @Query("apiKey") apiKey: String = Constant.API_KEY
    ) : NewsResponse

}