package com.ntdtrong.news.features.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ntdtrong.news.source.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {
    fun getNews(query: String)= viewModelScope.launch {
        newsRepository.getNews(query)
            .collect {
                Log.e("trong", "${it.size}")
            }
    }
}