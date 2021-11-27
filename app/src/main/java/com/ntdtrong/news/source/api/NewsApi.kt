package com.ntdtrong.news.source.api

import com.ntdtrong.news.data.model.NewsResponse
import retrofit2.http.GET

interface NewsApi {
    @GET("")
    suspend fun getNews(): NewsResponse
}