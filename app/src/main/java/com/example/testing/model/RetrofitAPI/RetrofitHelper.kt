package com.example.testing.model.RetrofitAPI

import android.os.Build
import com.google.gson.GsonBuilder
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
        private const val url = "https://newsapi.org/v2/"

        fun getInstance(): Retrofit {
                val Gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
                return Retrofit.Builder().baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create(Gson)).build()
        }
}