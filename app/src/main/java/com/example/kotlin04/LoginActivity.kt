package com.example.kotlin04

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        container.setOnTouchListener { v, event ->
            container.hideKeyboard()
            true
        }
        tv_sign_up.setOnClickListener {
            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
        tv_forgot.setOnClickListener {
            val intent = Intent(this@LoginActivity, EmailActivity::class.java)
            startActivity(intent)
        }

        edt_email.setText(intent.getStringExtra("email"))
        edt_password.setText(intent.getStringExtra("password"))
        btn_login.setOnClickListener {
            if (checkUser()) {
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this@LoginActivity, "failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun checkUser(): Boolean {
        val pref = applicationContext.getSharedPreferences("userinfo", MODE_PRIVATE)
        val email = pref.getString("email", "")
        val password = pref.getString("password", "")
        return email == edt_email.text.toString() && password == edt_password.text.toString()
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}