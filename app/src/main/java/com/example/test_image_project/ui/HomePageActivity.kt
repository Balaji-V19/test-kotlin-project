package com.example.test_image_project.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_image_project.R
import com.example.test_image_project.model.ImageModelResponse
import com.example.test_image_project.view_model.ImageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomePageActivity : AppCompatActivity() {
    private val viewModel: ImageViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.fetchImages()
        var items: List<ImageModelResponse>

        viewModel.imageList
            .observe(this) { data ->
                items = data
                recyclerView.adapter = ImageRecyclerViewAdapter(context = this, items = items)
        }

    }
}