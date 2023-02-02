package com.example.testing

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.testing.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback
import kotlin.math.log
import com.example.testing.News as News

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getNews()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_dashboard, R.id.navigation_favourites,  R.id.navigation_addRecords ,  R.id.navigation_search ,  R.id.navigation_settings
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
private fun getNews(){
    val news= NewsService.newsInstance.getHeadlines("in",1)
    news.enqueue(object:retrofit2.Callback<News>{
        override fun onResponse(call: Call<News>, response: Response<News>) {
            val news=response.body()
            if(news!=null){
                Log.d("DSUStudentPortal",news.toString())
            }
        }

        override fun onFailure(call: Call<News>, t: Throwable) {
            Log.d("DSUStudentPortal","Error",t)
        }
    })
}