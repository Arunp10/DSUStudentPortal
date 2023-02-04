package com.example.testing.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.testing.R
import com.example.testing.model.RetrofitAPI.News
import java.text.SimpleDateFormat

class SettingAdapter (
    private var myNews: ArrayList<News>,
    val itemClickHandler: (News) -> Unit,
):RecyclerView.Adapter<SettingAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById<CardView>(R.id.newscard)!!
        val imageView = itemView.findViewById<ImageView>(R.id.news_image)!!
        val titleTextView = itemView.findViewById<TextView>(R.id.news_title)!!
        val authorTextView = itemView.findViewById<TextView>(R.id.news_author)!!
        val descriptionTextView = itemView.findViewById<TextView>(R.id.news_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val newsView = inflater.inflate(R.layout.news_list, parent, false)
        val viewHolder = ViewHolder(newsView)
        viewHolder.cardView.setOnClickListener {
            if (viewHolder.adapterPosition >= 0 && viewHolder.adapterPosition < myNews.size) {
                itemClickHandler(myNews[viewHolder.adapterPosition])
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val news: News = myNews[position]
        val titleTextView = viewHolder.titleTextView
        titleTextView.text = news.title
        val authorTextView = viewHolder.authorTextView
        val publishingDetails = "${news.author ?: "Aroon Kumar"} at ${
            (SimpleDateFormat("dd MMM yyyy, HH:mm:ss ").format(news.publishedAt))
        }"
        authorTextView.text = publishingDetails
        val descriptionTextView = viewHolder.descriptionTextView
        descriptionTextView.text = news.description
    }
    override fun getItemCount(): Int {
        return myNews.size
    }

    fun addData(newData: List<News>) {
        myNews.addAll(newData)
        notifyDataSetChanged()
    }
}