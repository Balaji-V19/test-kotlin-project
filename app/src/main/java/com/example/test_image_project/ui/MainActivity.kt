package com.example.test_image_project.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.test_image_project.R
import com.example.test_image_project.data.di.repositoryModule
import com.example.test_image_project.data.di.mapperModule
import com.example.test_image_project.data.di.networkModule
import com.example.test_image_project.data.di.useCaseModule
import com.example.test_image_project.data.di.viewModelModule
import org.koin.core.context.startKoin

class MainActivity : AppCompatActivity() {

    private val SPLASH_DISPLAY_LENGTH = 2000

    private val appModules by lazy {
        listOf(
            viewModelModule,
            repositoryModule,
            mapperModule,
            useCaseModule,
            networkModule
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startKoin {
            modules(appModules)
        }


        Handler().postDelayed({
            val mainIntent = Intent(this@MainActivity, HomePageActivity::class.java)
            startActivity(mainIntent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
            finish()
        }, SPLASH_DISPLAY_LENGTH.toLong())
    }
}