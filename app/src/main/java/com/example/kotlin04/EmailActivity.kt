package com.example.kotlin04

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_email.*

class EmailActivity : AppCompatActivity() {
    private var time = 31
    private lateinit var runnable: Runnable
    private lateinit var handler: Handler

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)
        tv_time.text = "Wait $time seconds before sending it"
        btn_resend.setOnClickListener {
            val intent = Intent(this@EmailActivity, ConfirmPassActivity::class.java)
            startActivity(intent)
        }
        handler = Handler(Looper.getMainLooper())

        runnable = Runnable {
            when {
                time > 0 -> {
                    time--
                    tv_time.text = "Wait $time seconds before sending it"
                    handler.postDelayed(runnable, 1000)
                }
                time == 0 -> {
                    handler.removeCallbacks(runnable)
                }
            }
        }

        handler.post(runnable)
    }
}