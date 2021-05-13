package com.example.kotlin04.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin04.R
import com.example.kotlin04.model.Index

class IndexAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var listIndex: ArrayList<Index> = arrayListOf()

    companion object {
        const val REGULAR_ITEM = 0
        const val FOOTER_ITEM = 1
    }

    private inner class RegularViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.tv_title)
        var desc: TextView = itemView.findViewById(R.id.tv_desc)
        fun bind(position: Int) {
            val recyclerViewModel = listIndex[position]
            title.text = recyclerViewModel.title
            desc.text = recyclerViewModel.desc
        }
    }

    private inner class FooterViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvLoadmore : TextView = itemView.findViewById(R.id.tv_loadmore)
        var imgLoadmore : ImageView = itemView.findViewById(R.id.img_loadmore)
        fun bind(position: Int) {

        }
    }

    override fun getItemCount(): Int {
        return listIndex.size
    }

    fun setData(data: ArrayList<Index>) {
        listIndex = data
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        val item = listIndex[position]
        if (item.viewType == REGULAR_ITEM) {
            return REGULAR_ITEM
        } else if (item.viewType == FOOTER_ITEM) {
            return FOOTER_ITEM
        }
        throw Exception("Error, unknown view type")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (listIndex[position].viewType) {
            REGULAR_ITEM -> (holder as IndexAdapter.RegularViewHolder).bind(position)
            FOOTER_ITEM -> (holder as IndexAdapter.FooterViewHolder).bind(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == REGULAR_ITEM) {
            return RegularViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_index, parent, false)
            )
        }
        return FooterViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_index_loading, parent, false)
        )
    }
     fun isLoading() : Boolean {
        return listIndex.last().viewType == FOOTER_ITEM
    }
    fun addFooter() {
        Log.d("endlessscroll", "addFooter")
        if (!isLoading()) {
            listIndex.add(Index("Footer", "", FOOTER_ITEM))
            notifyItemInserted(listIndex.size - 1)
        }
    }

    fun removeFooter() {
        Log.d("endlessscroll", "removeFooter")
        if (isLoading()) {
            listIndex.removeAt(listIndex.size - 1)
            notifyItemRemoved(listIndex.size - 1)
        }
    }

    fun addItems(items : ArrayList<Index>) {
        val lastPos = listIndex.size - 1
        listIndex.addAll(items)
        notifyItemRangeInserted(lastPos, items.size)
    }
    fun delItem(position: Int) {
        listIndex.removeAt(position)
        notifyDataSetChanged()
    }


}