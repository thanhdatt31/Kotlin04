package com.example.kotlin04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_coin.*

class CoinActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin)
        tabLayout.addTab(tabLayout.newTab().setText("General"))
        tabLayout.addTab(tabLayout.newTab().setText("Technical Section"))
        tabLayout.addTab(tabLayout.newTab().setText("Markets"))
        tabLayout.addTab(tabLayout.newTab().setText("Charts"))
    }
}