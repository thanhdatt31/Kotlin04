package com.example.kotlin04.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin04.R
import com.example.kotlin04.adapter.NewsAdapter
import com.example.kotlin04.model.News
import kotlinx.android.synthetic.main.fragment_news.view.*


class NewsFragment : Fragment() {
    private var listNews: ArrayList<News> = arrayListOf()
    private var newsAdapter: NewsAdapter = NewsAdapter()
    private var callback2: OnBackPressedCallback? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        view.tabLayout.addTab(view.tabLayout.newTab().setText("EDITORIAL"))
        view.tabLayout.addTab(view.tabLayout.newTab().setText("CRYPTO NEWS"))
        view.tabLayout.addTab(view.tabLayout.newTab().setText("RAW MATERIAL"))
        view.tabLayout.addTab(view.tabLayout.newTab().setText("EDITORIAL"))
        addDumpData()
        view.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            newsAdapter.setData(listNews)
            adapter = newsAdapter
        }
        view.img_main.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.container_news, NewsArticleFragment())
            transaction.addToBackStack("")
            transaction.commit()
            view.tv_menu.text = "EDITORIAL NEWS"
        }
//        callback2 = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                view.tv_menu.text = "NEWS"
//
//            }
//
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(callback2 as OnBackPressedCallback)
        return view
    }

    override fun onPause() {
        callback2?.remove()
        super.onPause()
    }

    override fun onResume() {
//        requireActivity().onBackPressedDispatcher.addCallback(callback2 as OnBackPressedCallback)
        super.onResume()
    }

    private fun addDumpData() {
        listNews.add(News("ATLANTIA", "ALT -3,87%", R.drawable.image_news_1))
        listNews.add(News("XIAOMI", "HKD -2,13%", R.drawable._390177))
        listNews.add(News("APPLE", "AAPL -0,91%", R.drawable.image_news_1))
    }


}