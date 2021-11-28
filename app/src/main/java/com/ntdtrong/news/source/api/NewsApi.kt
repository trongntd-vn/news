package com.ntdtrong.news.source.api

import com.ntdtrong.news.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("apiKey") key: String = "e2c24a0d7b3b4bcc8b16477c56d790d3"
    ): NewsResponse
}