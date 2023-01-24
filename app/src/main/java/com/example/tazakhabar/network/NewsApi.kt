package com.example.tazakhabar.network

import com.example.tazakhabar.models.NewsResponse
import com.example.tazakhabar.utils.Constants.KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("v2/top-headlines?apiKey=$KEY")
    suspend fun getNews(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("pagesize") pagesize: Int
    ): Response<NewsResponse>

}