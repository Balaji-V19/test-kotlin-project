package com.example.test_image_project.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.test_image_project.R

class MainActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Handler().postDelayed({
            val mainIntent = Intent(this@MainActivity, HomePageActivity::class.java)
            startActivity(mainIntent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }
}