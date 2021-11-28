package com.ntdtrong.news.source.remote

import com.ntdtrong.news.data.model.Article
import com.ntdtrong.news.source.api.NewsApi
import dagger.Lazy
import javax.inject.Inject

class NewsRemoteDataSource @Inject constructor(
    private val newsApi: Lazy<NewsApi>
) {
    suspend fun getNews(query: String): List<Article> {
        return newsApi.get().getNews(query).articles
    }
}