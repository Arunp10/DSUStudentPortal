package com.example.testing.model.RetrofitAPI

import com.example.testing.model.RetrofitAPI.News
import com.google.gson.annotations.SerializedName

data class NewsResponse(
 @SerializedName("status")
 val status: String,
 @SerializedName("totalResults")
 val totalResults: Int,
 @SerializedName("articles")
 val newsList: List<News>
) {
}