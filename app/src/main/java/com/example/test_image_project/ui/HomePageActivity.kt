package com.example.test_image_project.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test_image_project.R
import com.example.test_image_project.databinding.ActivityHomePageBinding
import com.example.test_image_project.model.ImageModelResponse
import com.example.test_image_project.view_model.ImageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomePageActivity : AppCompatActivity() {
    private val viewModel: ImageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //data binding
        val binding: ActivityHomePageBinding = DataBindingUtil.setContentView(this, R.layout.activity_home_page)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val addImageUrlButton: Button = findViewById(R.id.addImageUrlButton)

        //do a fetch
        viewModel.fetchImages()
        var items: List<ImageModelResponse>

        viewModel.imageList
            .observe(this) { data ->
                items = data
                recyclerView.adapter = ImageRecyclerViewAdapter(context = this, items = items, viewModel = viewModel)
        }

        addImageUrlButton.setOnClickListener {
            val mainIntent = Intent(this@HomePageActivity, AddOrEditImageActivity::class.java)
            startActivity(mainIntent)
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.fetchImages()
    }
}