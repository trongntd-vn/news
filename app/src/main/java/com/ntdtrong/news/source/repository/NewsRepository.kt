package com.ntdtrong.news.source.repository

import com.ntdtrong.news.source.remote.NewsRemoteDataSource
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val remoteDataSource: NewsRemoteDataSource
) {
    fun getNews(query: String) = flow {
        emit(remoteDataSource.getNews(query))
    }
}