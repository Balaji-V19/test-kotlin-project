package com.example.test_image_project.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.test_image_project.R
import com.example.test_image_project.databinding.ActivityAddImageBinding
import com.example.test_image_project.view_model.AddImageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddImageActivity : AppCompatActivity() {
    private val viewModel: AddImageViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityAddImageBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_image)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val submitAddImageButton: Button = findViewById(R.id.submitAddImageButton)
        val imageUrlEditText: EditText = findViewById(R.id.imageUrlEditText)
        val nameEditText: EditText = findViewById(R.id.nameEditText)

        submitAddImageButton.setOnClickListener {
            viewModel.addNewImageAndName(imageUrlEditText.text.toString(),nameEditText.text.toString())
        }

        viewModel.showToastMessage.observe(this) { message ->
            message?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                viewModel.showToastMessage.value =
                    null
            }
        }

        viewModel.imageResponse
            .observe(this) { data ->
                if(data!=null){
                    finish()
                }
            }
    }
}