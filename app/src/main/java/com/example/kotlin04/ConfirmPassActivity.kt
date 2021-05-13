package com.example.kotlin04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_confirm_pass.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class ConfirmPassActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_pass)
        val pref = applicationContext.getSharedPreferences("userinfo", MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString("password", "123")
        editor.apply()
        btn_resend.setOnClickListener {
            val intent = Intent(this@ConfirmPassActivity,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}