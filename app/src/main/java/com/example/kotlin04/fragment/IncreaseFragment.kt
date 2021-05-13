package com.example.kotlin04.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlin04.R
import com.example.kotlin04.adapter.ViewpagerAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_increase.view.*


class IncreaseFragment : Fragment() {
    private var listPosition = ArrayList<Int>()
    private var callback: OnBackPressedCallback? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_increase, container, false)
        view.tabLayout.addTab(view.tabLayout.newTab().setText("INDEX"))
        view.tabLayout.addTab(view.tabLayout.newTab().setText("SHARES"))
        view.tabLayout.addTab(view.tabLayout.newTab().setText("CURRENCIES"))
        view.tabLayout.addTab(view.tabLayout.newTab().setText("FUTURES"))
        listPosition.add(0)
        val fragmentList = arrayListOf(
            IndexFragment(),
            ShareFragment(),
            CurrenciesFragment(),
            FutureFragment()
        )
        val adapter = ViewpagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        view.viewpager2.adapter = adapter
        view.viewpager2.isUserInputEnabled = false
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (listPosition.size > 0) {
                    if (view.tabLayout.selectedTabPosition == listPosition[listPosition.size - 1]) {
                        listPosition.removeAt(listPosition.size - 1)
                    }
                    if (listPosition.size > 0) {
                        view.tabLayout.selectTab(view.tabLayout.getTabAt(listPosition[listPosition.size - 1]))
                        listPosition.removeAt(listPosition.size - 1)
                    }
                }
                if (listPosition.size == 0) {
                    if (view.tabLayout.selectedTabPosition == 0) {
                        showDialog()
                    }
                    view.tabLayout.selectTab(view.tabLayout.getTabAt(0))
                }
            }
        }

        view.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                listPosition.add(tab.position)
                view.viewpager2.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
        view.viewpager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                view.tabLayout.selectTab(view.tabLayout.getTabAt(position))
                super.onPageSelected(position)
            }
        })
        requireActivity().onBackPressedDispatcher.addCallback(callback as OnBackPressedCallback)


        return view
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Exit")
        builder.setMessage("Are u sure wanna exit our app ?")

        builder.setPositiveButton(android.R.string.ok) { dialog, which ->
            requireActivity().finish()
        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, which ->
            dialog.dismiss()
        }

        builder.show()
    }


    override fun onPause() {
        callback?.remove()
        super.onPause()
    }

    override fun onResume() {
        requireActivity().onBackPressedDispatcher.addCallback(callback as OnBackPressedCallback)
        super.onResume()
    }
}


