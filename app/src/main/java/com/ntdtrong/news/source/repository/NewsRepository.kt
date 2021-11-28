package com.ntdtrong.news.source.repository

import com.ntdtrong.news.helper.NetworkHelper
import com.ntdtrong.news.source.local.NewsLocalDataSource
import com.ntdtrong.news.source.remote.NewsRemoteDataSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource,
    private val localDataSource: NewsLocalDataSource,
    private val networkHelper: NetworkHelper
) {
    fun getNews(query: String) = flow {
        if (networkHelper.isNetworkConnected()) {
            kotlin.runCatching { remoteDataSource.getNews(query) }
                .fold({
                    emit(Pair(it, false))
                }, {
                    val cachedArticles = localDataSource.getNews(query)
                    emit(Pair(cachedArticles, true))
                })
        } else {
            val cachedArticles = localDataSource.getNews(query)
            emit(Pair(cachedArticles, true))
        }
    }
}