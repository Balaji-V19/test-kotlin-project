package com.example.test_image_project.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_image_project.R
import com.example.test_image_project.model.ImageModel
import com.example.test_image_project.view_model.ImageViewModel

class HomePageActivity : AppCompatActivity() {
    private lateinit var viewModel: ImageViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application
            ))[ImageViewModel::class.java]


        viewModel.fetchImages()


        var items: List<ImageModel>

        viewModel.imageList
            .observe(this) { data ->
                items = data
                recyclerView.adapter = ImageRecyclerViewAdapter(context = this, items = items)
        }

    }
}