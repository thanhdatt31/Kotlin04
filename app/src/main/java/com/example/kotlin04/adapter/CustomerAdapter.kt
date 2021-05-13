package com.example.kotlin04.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin04.R
import com.example.kotlin04.model.Customer

class CustomerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listCustomer: ArrayList<Customer> = arrayListOf()

    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
        const val VIEW_TYPE_THREE = 3
    }

    private inner class View1ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.tv_title_customer)
        var avatar: ImageView = itemView.findViewById(R.id.img_avatar_customer)
        var line: View = itemView.findViewById(R.id.customer_line)
        fun bind(position: Int) {
            val recyclerViewModel = listCustomer[position]
            title.text = recyclerViewModel.title
            avatar.setImageResource(recyclerViewModel.avatar)
            if (position == 3) {
                line.visibility = View.VISIBLE
            }
        }
    }


    private inner class View2ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.tv_title_customer_2)
        var avatar: ImageView = itemView.findViewById(R.id.img_avatar_customer_2)
        var line: View = itemView.findViewById(R.id.customer_line_2)
        var buttonTitle: TextView = itemView.findViewById(R.id.tv_title)
        var buttonBg: ImageView = itemView.findViewById(R.id.bg_title)
        fun bind(position: Int) {
            val recyclerViewModel = listCustomer[position]
            title.text = recyclerViewModel.title
            avatar.setImageResource(recyclerViewModel.avatar)
            if (position != 7) {
                line.visibility = View.INVISIBLE
            }
            if (position == 4) {
                buttonTitle.visibility = View.VISIBLE
                buttonBg.visibility = View.VISIBLE
            }
        }
    }

    private inner class View3ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.tv_title_customer_2)
        var avatar: ImageView = itemView.findViewById(R.id.img_avatar_customer_2)
        var line: View = itemView.findViewById(R.id.customer_line_2)
        var buttonTitle: TextView = itemView.findViewById(R.id.tv_title)
        var buttonBg: ImageView = itemView.findViewById(R.id.bg_title)
        fun bind(position: Int) {
            val recyclerViewModel = listCustomer[position]
            title.text = recyclerViewModel.title
            avatar.setImageResource(recyclerViewModel.avatar)
            line.visibility = View.GONE
            buttonTitle.text = "Markets"
            buttonTitle.visibility = View.VISIBLE
            buttonBg.visibility = View.VISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ONE) {
            return View1ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_customer_one, parent, false)
            )
        }
        if (viewType == VIEW_TYPE_THREE) {
            return View3ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_customer_two, parent, false)
            )
        }
        return View2ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_customer_two, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (listCustomer[position].viewType) {
            VIEW_TYPE_ONE -> (holder as View1ViewHolder).bind(position)
            VIEW_TYPE_TWO -> (holder as View2ViewHolder).bind(position)
            VIEW_TYPE_THREE -> (holder as View3ViewHolder).bind(position)
        }
    }

    override fun getItemCount(): Int {
        return listCustomer.size
    }

    override fun getItemViewType(position: Int): Int {
        return listCustomer[position].viewType
    }

    fun setData(data: ArrayList<Customer>) {
        listCustomer = data
        notifyDataSetChanged()
    }
}