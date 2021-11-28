package com.ntdtrong.news.features.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ntdtrong.news.data.model.Article
import com.ntdtrong.news.rule.CoroutineTestRule
import com.ntdtrong.news.source.local.NewsLocalDataSource
import com.ntdtrong.news.source.repository.NewsRepository
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    @Rule
    @JvmField
    val coroutineRule = CoroutineTestRule()

    @Rule
    @JvmField
    val archRule = InstantTaskExecutorRule()

    private val newsRepository: NewsRepository = mockk()
    private val localDataSource: NewsLocalDataSource = mockk()
    private val viewModel = HomeViewModel(newsRepository, localDataSource)

    @Test
    fun `getNews should emit new data when success`() {
        val articles = listOf<Article>(mockk(), mockk())
        val response = Pair(articles, true)
        every { newsRepository.getNews(any()) } returns flow { emit(response) }

        viewModel.getNews("test")

        Assert.assertEquals(articles, viewModel.articles.value)
        every { newsRepository.getNews("test") }
    }

    @Test
    fun `getNews should emit empty list when error`() {
        every { newsRepository.getNews(any()) } returns flow { throw Throwable() }

        viewModel.getNews("test")

        Assert.assertEquals(emptyList<Article>(), viewModel.articles.value)
        every { newsRepository.getNews("test") }
    }

    @Test
    fun `getNews should cache data if receive from api`() {
        val articles = listOf<Article>(mockk(), mockk())
        val response = Pair(articles, false)
        every { newsRepository.getNews(any()) } returns flow { emit(response) }
        coEvery { localDataSource.saveNews(any(), any()) } just Runs

        viewModel.getNews("test")

        coVerify { localDataSource.saveNews("test", articles) }
    }

    @Test
    fun `getNews should not cache data if receive from cache`() {
        val articles = listOf<Article>(mockk(), mockk())
        val response = Pair(articles, true)
        var hasCallSaveCache = false
        every { newsRepository.getNews(any()) } returns flow { emit(response) }
        coEvery { localDataSource.saveNews(any(), any()) } answers {
            hasCallSaveCache = true
        }

        viewModel.getNews("test")

        Assert.assertEquals(false, hasCallSaveCache)
    }
}