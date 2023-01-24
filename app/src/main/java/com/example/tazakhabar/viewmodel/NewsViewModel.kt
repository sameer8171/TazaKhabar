package com.example.tazakhabar.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tazakhabar.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {
    val newsLiveData get() = newsRepository.newsLiveData

    fun getNews(country: String, page: Int, category: String) {
        viewModelScope.launch {
            newsRepository.getNews(country = country, page = page, category = category)
        }
    }

}