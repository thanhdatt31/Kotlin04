package com.example.kotlin04.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin04.R
import com.example.kotlin04.model.News

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    var listNews: ArrayList<News> = arrayListOf()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.tv_title)
        var percent: TextView = itemView.findViewById(R.id.tv_percent)
        var avatar: ImageView = itemView.findViewById(R.id.img_avatar_customer_2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news: News = listNews[position]
        holder.title.text = news.title
        holder.percent.text = news.percent
        holder.avatar.setImageResource(news.imageId)
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    fun setData(data: ArrayList<News>) {
        listNews = data
        notifyDataSetChanged()
    }
}