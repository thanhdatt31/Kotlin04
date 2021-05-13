package com.example.kotlin04.fragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin04.R
import com.example.kotlin04.adapter.IndexAdapter
import com.example.kotlin04.listener.SwipeToDeleteCallback
import com.example.kotlin04.model.Index
import kotlinx.android.synthetic.main.fragment_index2.view.*
import java.util.*
import kotlin.collections.ArrayList


class IndexFragment : Fragment() {
    private val handler = Handler(Looper.getMainLooper())
    private var linearLayoutManager = LinearLayoutManager(context)
    private var listIndex: ArrayList<Index> = arrayListOf()
    private var indexAdapter: IndexAdapter = IndexAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_index2, container, false)
        addDumbData()
        view.recyclerView.apply {
            layoutManager = linearLayoutManager
            indexAdapter.setData(listIndex)
            adapter = indexAdapter
            val swipeDelete = object : SwipeToDeleteCallback() {
                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    indexAdapter.delItem(viewHolder.adapterPosition)
                }
            }
            val touchHelper = ItemTouchHelper(swipeDelete)
            touchHelper.attachToRecyclerView(view.recyclerView)
        }
        view.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!indexAdapter.isLoading()) {
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() >= linearLayoutManager.itemCount - 1) {
                        view.recyclerView.post {
                            indexAdapter.addFooter()
                        }

                        handler.postDelayed({
                            indexAdapter.removeFooter()
                            val newIndex = ArrayList<Index>()
                            for (i in listIndex.size..listIndex.size + 10) {
                                newIndex.add(Index("DOWN JONES $i", "NYSE", 0))
                            }
                            indexAdapter.addItems(newIndex)
                        }, 2500)
                    }
                }

                if (dy < 0 && !view.fab.isShown)
                    view.fab.show();
                else if (dy > 0 && view.fab.isShown)
                    view.fab.hide();
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                    view.fab.show();
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
        view.fab.setOnClickListener {
            val rnd = Random()
            val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            view.fab.backgroundTintList = ColorStateList.valueOf(color)
        }
        return view
    }

    private fun addDumbData() {
        listIndex.add(Index("DOWN JONES", "NYSE", IndexAdapter.REGULAR_ITEM))
        listIndex.add(Index("FTSE 100", "LONDON", IndexAdapter.REGULAR_ITEM))
        listIndex.add(Index("IBEX 35", "MADRID", IndexAdapter.REGULAR_ITEM))
        listIndex.add(Index("DAX", "XETRA", IndexAdapter.REGULAR_ITEM))
        listIndex.add(Index("DOWN JONES", "NYSE", IndexAdapter.REGULAR_ITEM))
        listIndex.add(Index("FTSE 100", "LONDON", IndexAdapter.REGULAR_ITEM))
        listIndex.add(Index("IBEX 35", "MADRID", IndexAdapter.REGULAR_ITEM))
        listIndex.add(Index("DAX", "XETRA", IndexAdapter.REGULAR_ITEM))
        listIndex.add(Index("IBEX 35", "MADRID", IndexAdapter.REGULAR_ITEM))
        listIndex.add(Index("DAX", "XETRA", IndexAdapter.REGULAR_ITEM))
    }


}