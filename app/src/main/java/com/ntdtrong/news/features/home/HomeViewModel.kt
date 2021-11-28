package com.ntdtrong.news.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ntdtrong.news.data.extension.postData
import com.ntdtrong.news.data.model.Article
import com.ntdtrong.news.source.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    val articles: LiveData<List<Article>> = MutableLiveData()

    fun getNews(query: String) = viewModelScope.launch {
        newsRepository.getNews(query)
            .flowOn(Dispatchers.IO)
            .catch {
                articles.postData(emptyList())
            }
            .collect {
                articles.postData(it)
            }
    }
}