package com.ntdtrong.news.source.api

import com.ntdtrong.news.BuildConfig
import com.ntdtrong.news.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("apiKey") key: String = BuildConfig.API_KEY
    ): NewsResponse
}