package com.example.kotlin04.fragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlin04.R
import kotlinx.android.synthetic.main.fragment_chart.view.*
import java.util.*


class ChartFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chart, container, false)
        view.tabLayout.addTab(view.tabLayout.newTab().setText("General"))
        view.tabLayout.addTab(view.tabLayout.newTab().setText("Technical Section"))
        view.tabLayout.addTab(view.tabLayout.newTab().setText("Markets"))
        view.tabLayout.addTab(view.tabLayout.newTab().setText("Charts"))
        view.fab.setOnClickListener {
            val rnd = Random()
            val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
            view.fab.backgroundTintList = ColorStateList.valueOf(color)
        }
        return view
    }

}