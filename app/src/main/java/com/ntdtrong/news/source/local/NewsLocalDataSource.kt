package com.ntdtrong.news.source.local

import com.google.gson.Gson
import com.ntdtrong.news.data.extension.fromJson
import com.ntdtrong.news.data.model.Article
import com.ntdtrong.news.helper.FileHelper
import javax.inject.Inject

class NewsLocalDataSource @Inject constructor(
    private val fileHelper: FileHelper
) {
    suspend fun getNews(query: String): List<Article> {
        val json = fileHelper.getCache(query)
        return if (json.isNotEmpty()) {
            Gson().fromJson<List<Article>>(json)
        } else {
            emptyList()
        }
    }

    suspend fun saveNews(query: String, articles: List<Article>) {
        val json = Gson().toJson(articles)
        fileHelper.saveCache(query, json)
    }
}