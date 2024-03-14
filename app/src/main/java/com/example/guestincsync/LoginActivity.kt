package com.example.guestincsync

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_login)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()

        val loginButton: Button = findViewById(R.id.loginButton)
        val registerLinkText: TextView = findViewById(R.id.textViewLinkToLogin)

        loginButton.setOnClickListener{
            Toast.makeText(this,"Logged in", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        registerLinkText.setOnClickListener{
            startActivity(Intent(this,RegisterActivity::class.java))
        }
    }
}