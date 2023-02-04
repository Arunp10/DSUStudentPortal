package com.example.testing.model.RetrofitAPI

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {
    @GET("top-headlines")

    suspend fun getNews(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
    ): Response<NewsResponse>
}