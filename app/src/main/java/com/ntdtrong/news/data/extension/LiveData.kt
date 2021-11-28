package com.ntdtrong.news.data.extension

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> LiveData<T>.postData(data: T) {
    (this as? MutableLiveData)?.postValue(data)
}