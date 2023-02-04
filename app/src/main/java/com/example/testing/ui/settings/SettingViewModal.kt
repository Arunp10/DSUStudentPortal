package com.example.testing.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testing.model.RetrofitAPI.NewsAPI
import com.example.testing.model.RetrofitAPI.RetrofitHelper
import com.example.testing.model.RetrofitAPI.News

class SettingViewModal() : ViewModel() {
    private val _newsList: MutableLiveData<ArrayList<News>> =
        MutableLiveData<ArrayList<News>>()
    val newsList: LiveData<ArrayList<News>>
        get() = _newsList
    private var currentPage = 1
    var canLoadMore = true
    companion object {
        const val pageSize = 10
    }
    //    @Synchronized
    suspend fun loadRecords() : List<News> {
        if (_newsList.value == null) {
            _newsList.value = ArrayList(0)
        }
        val newsApi = RetrofitHelper.getInstance().create(NewsAPI::class.java)
        val result =
            newsApi.getNews("us", "1dbf08ceb411481a8f1b7800b3419523", pageSize, currentPage)
        if (result.body() != null) {
            val fetchedNews = result.body()!!.newsList
            _newsList.value!!.addAll(fetchedNews)
            currentPage += 1
            if(fetchedNews.size < pageSize) {
                canLoadMore = false
            }
            return fetchedNews
        }
        return ArrayList(0)
    }
}
