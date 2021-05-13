package com.example.kotlin04

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        container.setOnTouchListener { v, event ->
            container.hideKeyboard()
            true
        }
        tv_login.setOnClickListener {
            val intentLogin = Intent(this@SignUpActivity, LoginActivity::class.java)
            intentLogin.putExtra("email", edt_email.text.toString())
            intentLogin.putExtra("password", edt_password.text.toString())
            startActivity(intentLogin)
        }
        btn_login.setOnClickListener {
            val pref = applicationContext.getSharedPreferences("userinfo", MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString("email", edt_email.text.toString())
            editor.putString("password", edt_password.text.toString())
            editor.apply()
            Toast.makeText(this@SignUpActivity, "Sign up successful", Toast.LENGTH_SHORT).show()
//            val handler = Handler(Looper.getMainLooper())
//            val runnable = Runnable {
//                val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
//                intent.putExtra("email", edt_email.text.toString())
//                intent.putExtra("password", edt_password.text.toString())
//                startActivity(intent)
//            }
//            handler.postDelayed(runnable, 5000)
        }

    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}