package com.example.testing.ui.settings

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.testing.databinding.ActivityNewsBinding
import com.example.testing.model.RetrofitAPI.News
import java.text.SimpleDateFormat

class NewsActivity : AppCompatActivity() {

    private lateinit var binding:ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val news = intent.getSerializableExtra("news") as? News

        val newsDetailPublisher: TextView = binding.newsDetailPublisher
        val publishingDetails = "${news?.author ?: "Aroon Kumar"} at ${(SimpleDateFormat("dd MMM yyyy, HH:mm:ss ").format(news?.publishedAt))}"
        newsDetailPublisher.text = publishingDetails

        val newsDetailTitle: TextView = binding.newsDetailTitle
        newsDetailTitle.text = news?.title

        val newsDetailDescription: TextView = binding.newsDetailDescription
        newsDetailDescription.text = news?.description

        val newsDetailContent: TextView = binding.newsDetailContent
        newsDetailContent.text = news?.content

    }
}