package com.example.testing.ui.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testing.R
import com.example.testing.databinding.FragmentSearchBinding
import com.example.testing.model.RetrofitAPI.News
import com.example.testing.ui.settings.NewsActivity
import kotlinx.coroutines.launch
import java.io.Serializable

class SettingFragment: Fragment() {
    var loading = false

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_search, container, false)
        val News = view.findViewById(R.id.searchNews) as RecyclerView

        val SettingModel: SettingViewModal =
            ViewModelProvider(requireActivity())[SettingViewModal::class.java]

        val adapter = SettingAdapter(
            SettingModel.newsList.value ?: ArrayList(0),
            ::onNewsClickHandler,
        )

        News.adapter = adapter
        News.layoutManager = LinearLayoutManager(view.context)
        if (SettingModel.newsList.value == null) {
            loadData(SettingModel, adapter)
        }

        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                if (SettingModel.canLoadMore && lastVisibleItemPosition == adapter.itemCount - 1) {
                    loadData(SettingModel, adapter)
                }
            }
        }
        News.addOnScrollListener(scrollListener)
        return view
    }
    private fun loadData(SettingModel : SettingViewModal, newsAdapter: SettingAdapter) {
        if (!loading) {
            loading = true
            lifecycleScope.launch {
                val newData = SettingModel.loadRecords()
                newsAdapter.addData(newData)
                Log.d("DSUStudentPortal", newData.size.toString())
                loading = false
            }
        }
    }
    private fun onNewsClickHandler(news: News) {
        activity?.let {
            val intent = Intent(it, NewsActivity::class.java)
            intent.putExtra(
                "news",
                news as Serializable
            )
            it.startActivityFromFragment(this, intent, 1)
        }
    }
}