package com.example.kotlin04

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlin04.adapter.ViewpagerAdapter
import com.example.kotlin04.fragment.ChartFragment
import com.example.kotlin04.fragment.CustomerFragment
import com.example.kotlin04.fragment.IncreaseFragment
import com.example.kotlin04.fragment.NewsFragment
import com.example.kotlin04.listener.IOnBackPressed
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val fragmentList = arrayListOf(
            IncreaseFragment(),
            ChartFragment(),
            NewsFragment(),
            CustomerFragment()
        )

        val adapter = ViewpagerAdapter(
            fragmentList,
            supportFragmentManager,
            lifecycle
        )
        viewPager.adapter = adapter
        viewPager.isUserInputEnabled = false
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        indicator_increase.visibility = View.VISIBLE
                        ic_increase_checked.visibility = View.VISIBLE
                        ic_increase_unchecked.visibility = View.INVISIBLE
                        indicator_chart.visibility = View.INVISIBLE
                        indicator_customer.visibility = View.INVISIBLE
                        indicator_news.visibility = View.INVISIBLE
                        ic_chart_checked.visibility = View.INVISIBLE
                        ic_chart_unchecked.visibility = View.VISIBLE
                        bottom_nav_bg.visibility = View.INVISIBLE
                    }
                    1 -> {
                        indicator_increase.visibility = View.INVISIBLE
                        ic_increase_checked.visibility = View.INVISIBLE
                        ic_increase_unchecked.visibility = View.VISIBLE
                        indicator_chart.visibility = View.VISIBLE
                        indicator_customer.visibility = View.INVISIBLE
                        indicator_news.visibility = View.INVISIBLE
                        ic_chart_checked.visibility = View.VISIBLE
                        ic_chart_unchecked.visibility = View.INVISIBLE
                        bottom_nav_bg.visibility = View.VISIBLE

                    }
                    2 -> {
                        indicator_news.visibility = View.VISIBLE
                        indicator_increase.visibility = View.INVISIBLE
                        indicator_customer.visibility = View.INVISIBLE
                        indicator_chart.visibility = View.INVISIBLE
                        ic_increase_checked.visibility = View.INVISIBLE
                        ic_increase_unchecked.visibility = View.VISIBLE
                        ic_chart_checked.visibility = View.INVISIBLE
                        ic_chart_unchecked.visibility = View.VISIBLE
                        bottom_nav_bg.visibility = View.VISIBLE
                    }
                    3 -> {
                        indicator_news.visibility = View.INVISIBLE
                        indicator_increase.visibility = View.INVISIBLE
                        indicator_customer.visibility = View.VISIBLE
                        indicator_chart.visibility = View.INVISIBLE
                        ic_increase_checked.visibility = View.INVISIBLE
                        ic_increase_unchecked.visibility = View.VISIBLE
                        ic_chart_checked.visibility = View.INVISIBLE
                        ic_chart_unchecked.visibility = View.VISIBLE
                        bottom_nav_bg.visibility = View.VISIBLE

                    }
                }
                super.onPageSelected(position)
            }
        })
        ic_chart_unchecked.setOnClickListener {
            viewPager.currentItem = 1
        }
        ic_increase_unchecked.setOnClickListener {
            viewPager.currentItem = 0
        }
        ic_news_unchecked.setOnClickListener {
            viewPager.currentItem = 2
        }
        ic_customer_unchecked.setOnClickListener {
            viewPager.currentItem = 3
        }
    }


}