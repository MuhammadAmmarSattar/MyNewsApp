package com.example.news_data.network

import com.example.core_ui.Constant
import com.example.news_data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {
    //https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=f6cdb4941ed04d25add50a9da3384517
    @GET("top-headlines")
    suspend fun getNewsList(
        @Query("country") country: String= Constant.COUNTRY,
        @Query("category") business: String= Constant.CATEGORY,
        @Query("apiKey") apiKey: String = Constant.API_KEY
    ): NewsResponse
}