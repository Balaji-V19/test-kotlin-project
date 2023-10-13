package com.example.test_image_project.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.test_image_project.R
import com.example.test_image_project.databinding.ActivityAddOrEditImageBinding
import com.example.test_image_project.view_model.AddImageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddOrEditImageActivity : AppCompatActivity() {
    private val viewModel: AddImageViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityAddOrEditImageBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_or_edit_image)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val submitAddImageButton: Button = findViewById(R.id.submitAddImageButton)
        val imageUrlEditText: EditText = findViewById(R.id.imageUrlEditText)
        val nameEditText: EditText = findViewById(R.id.nameEditText)

        val imageUrlFromPreviousPage = intent.getStringExtra("imageUrl")
        val nameFromPreviousPage = intent.getStringExtra("name")
        val idFromPreviousPage = intent.getStringExtra("id")

        if(!imageUrlFromPreviousPage.isNullOrEmpty()){
            imageUrlEditText.setText(imageUrlFromPreviousPage)
            nameEditText.setText(nameFromPreviousPage)
        }

        submitAddImageButton.setOnClickListener {
            if(!imageUrlFromPreviousPage.isNullOrEmpty()){
                idFromPreviousPage?.let { it1 ->
                    viewModel.editImageAndName(
                        imageUrlEditText.text.toString(),
                        nameEditText.text.toString(),
                        it1
                    )
                }
            }else {
                viewModel.addNewImageAndName(
                    imageUrlEditText.text.toString(),
                    nameEditText.text.toString()
                )
            }
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