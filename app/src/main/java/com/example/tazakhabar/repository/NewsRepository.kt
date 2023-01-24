package com.example.tazakhabar.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tazakhabar.models.Article
import com.example.tazakhabar.models.NewsResponse
import com.example.tazakhabar.network.NewsApi
import com.example.tazakhabar.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApi: NewsApi) {

    private val _newsLiveData = MutableLiveData<NetworkResult<List<Article>>>()
    val newsLiveData: LiveData<NetworkResult<List<Article>>>
        get() = _newsLiveData


    suspend fun getNews(country:String,page:Int,category:String){
        _newsLiveData.postValue(NetworkResult.Loading())
         val response = newsApi.getNews(country = country, pagesize = page, category = category)
        if (response.isSuccessful && response.body() != null){
          _newsLiveData.postValue(NetworkResult.Success(response.body()?.articles ?: emptyList()))
        }else if (response.errorBody() != null){
              val errObj =JSONObject(response.errorBody()!!.charStream().readText())
              _newsLiveData.postValue(NetworkResult.Error(errObj.getString("message")))
        }else{
            _newsLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

}