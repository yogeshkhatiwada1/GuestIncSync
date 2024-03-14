package com.example.guestincsync

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.hide()

        setContentView(R.layout.activity_splash_screen)
        val splashGif = findViewById<ImageView>(R.id.splashScreenImageView)
        Glide.with(this).asGif().load(R.drawable.guestincsynclogo).into(splashGif)
        Handler().postDelayed({
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        },2500)
    }
}