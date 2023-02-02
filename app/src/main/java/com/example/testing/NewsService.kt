package com.example.testing

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=us&apiKey=a55000c1839d466184f61ea2de3a4e33
const val BASE_URL="https://newsapi.org/"
const val API_KEY="a55000c1839d466184f61ea2de3a4e33"
interface NewsInterface{

    @GET(value="v2/top-headlines?apikey=$API_KEY")
    fun getHeadlines(@Query("country") country:String,@Query("page") page:Int): Call<News>
}
//v2/top-headlines?apikey=a55000c1839d466184f61ea2de3a4e33&country=in&page=1
object NewsService{
    val newsInstance :NewsInterface
    init{
        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance=retrofit.create(NewsInterface::class.java)
    }

}