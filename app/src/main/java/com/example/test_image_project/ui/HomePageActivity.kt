package com.example.test_image_project.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_image_project.R
import com.example.test_image_project.model.ImageModel

class HomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val items = listOf(
            ImageModel("https://loremflickr.com/640/480",
                "2023-10-11T14:55:44.534Z",
                "name 1","1"),
            ImageModel("https://loremflickr.com/640/480",
                "2023-10-11T14:55:44.534Z",
                "name 1","1"),
            ImageModel("https://loremflickr.com/640/480",
                "2023-10-11T14:55:44.534Z",
                "name 1","1"),
            ImageModel("https://loremflickr.com/640/480",
                "2023-10-11T14:55:44.534Z",
                "name 1","1"),
        )

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ImageRecyclerViewAdapter(context = this, items = items)
    }
}