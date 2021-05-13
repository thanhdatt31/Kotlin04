package com.example.kotlin04

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlin04.adapter.ViewpagerAdapter
import com.example.kotlin04.onboarding.FirstScreen
import com.example.kotlin04.onboarding.SecondScreen
import com.example.kotlin04.onboarding.ThirdScreen
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_onboarding.*


class OnboardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        if (restorePrefData()) {
            val mainActivity = Intent(this@OnboardingActivity, LoginActivity::class.java)
            startActivity(mainActivity)
            finish()
        }
        val fragmentList = arrayListOf(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )
        val adapter = ViewpagerAdapter(
            fragmentList,
            supportFragmentManager,
            lifecycle
        )


        viewPager.adapter = adapter
        indicator_1_selected.visibility = View.VISIBLE

        tv_skip.setOnClickListener {
            val intent = Intent(this@OnboardingActivity, LoginActivity::class.java)
            savePrefsData()
            startActivity(intent)
        }
        btn_back.setOnClickListener {
            val currentPos = viewPager.currentItem
            if (currentPos > 0) {
                viewPager.currentItem = currentPos - 1
            }
        }
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            @SuppressLint("SetTextI18n")
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    2 -> {
                        tv_button.text = "START"
                        indicator_3_selected.visibility = View.VISIBLE
                        indicator_1_selected.visibility = View.GONE
                        indicator_2_selected.visibility = View.GONE
                        tv_button.setOnClickListener {
                            val intent = Intent(this@OnboardingActivity, LoginActivity::class.java)
                            savePrefsData()
                            startActivity(intent)
                        }
                        btn_next.setOnClickListener {
                            val intent = Intent(this@OnboardingActivity, LoginActivity::class.java)
                            savePrefsData()
                            startActivity(intent)
                        }

                    }
                    1 -> {
                        tv_button.text = "NEXT"
                        indicator_1_selected.visibility = View.GONE
                        indicator_2_selected.visibility = View.VISIBLE
                        indicator_3_selected.visibility = View.GONE
                        tv_button.setOnClickListener {
                            viewPager.currentItem = 2
                        }
                        btn_next.setOnClickListener {
                            viewPager.currentItem = 2
                        }


                    }
                    0 -> {
                        tv_button.text = "NEXT"
                        indicator_1_selected.visibility = View.VISIBLE
                        indicator_2_selected.visibility = View.GONE
                        indicator_3_selected.visibility = View.GONE

                        tv_button.setOnClickListener {
                            viewPager.currentItem = 1
                        }
                        btn_next.setOnClickListener {
                            viewPager.currentItem = 1
                        }
                    }
                }
                super.onPageSelected(position)
            }

        })


    }

    private fun savePrefsData() {
        val pref = applicationContext.getSharedPreferences("myPrefs", MODE_PRIVATE)
        val editor = pref.edit()
        editor.putBoolean("isIntroOpnend", true)
        editor.apply()
    }

    private fun restorePrefData(): Boolean {
        val pref = applicationContext.getSharedPreferences("myPrefs", MODE_PRIVATE)
        return pref.getBoolean("isIntroOpnend", false)
    }
}