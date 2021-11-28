package com.ntdtrong.news.source.repository

import com.ntdtrong.news.data.model.Article
import com.ntdtrong.news.helper.NetworkHelper
import com.ntdtrong.news.source.local.NewsLocalDataSource
import com.ntdtrong.news.source.remote.NewsRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsRepositoryTest {
    private val remoteDataSource: NewsRemoteDataSource = mockk()
    private val localDataSource: NewsLocalDataSource = mockk()
    private val networkHelper: NetworkHelper = mockk()
    private val repository = NewsRepository(remoteDataSource, localDataSource, networkHelper)

    @Test
    fun `getNews should return new data when call api success`() = runBlockingTest {
        val onlineArticles = listOf<Article>(mockk())
        val offlineArticles = listOf<Article>(mockk())
        every { networkHelper.isNetworkConnected() } returns true
        coEvery { remoteDataSource.getNews(any()) } returns onlineArticles
        coEvery { localDataSource.getNews(any()) } returns offlineArticles

        val result = repository.getNews("test").first()

        Assert.assertEquals(onlineArticles, result.first)
        Assert.assertEquals(false, result.second)
        coVerify { remoteDataSource.getNews("test") }
    }

    @Test
    fun `getNews should return cached data when call api failed`() = runBlockingTest {
        val articles = listOf<Article>(mockk())
        every { networkHelper.isNetworkConnected() } returns true
        coEvery { remoteDataSource.getNews(any()) } throws Throwable()
        coEvery { localDataSource.getNews(any()) } returns articles

        val result = repository.getNews("test").first()

        Assert.assertEquals(articles, result.first)
        Assert.assertEquals(true, result.second)
        coVerify { remoteDataSource.getNews("test") }
        coVerify { localDataSource.getNews("test") }
    }

    @Test
    fun `getNews should return cached data when no connection`() = runBlockingTest {
        val onlineArticles = listOf<Article>(mockk())
        val offlineArticles = listOf<Article>(mockk())
        every { networkHelper.isNetworkConnected() } returns false
        coEvery { remoteDataSource.getNews(any()) } returns onlineArticles
        coEvery { localDataSource.getNews(any()) } returns offlineArticles

        val result = repository.getNews("test").first()

        Assert.assertEquals(offlineArticles, result.first)
        Assert.assertEquals(true, result.second)
        coVerify { localDataSource.getNews("test") }
    }
}