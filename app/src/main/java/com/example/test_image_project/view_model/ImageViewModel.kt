package com.example.test_image_project.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_image_project.data.ApiService
import com.example.test_image_project.model.ImageModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageViewModel : ViewModel() {
    private val apiService = ApiService.create()

    private val _imageList = MutableLiveData<List<ImageModel>>()
    val imageList: LiveData<List<ImageModel>> = _imageList

    fun fetchImages() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getImages()
                if (response.isSuccessful) {
                    _imageList.postValue(response.body())
                    println(response.body())
                } else {
                    Log.d("Image fetching error", "fetchImages: ${response}")
                }
            } catch (e: Exception) {
                Log.d("Image fetching error", "fetchImages: ${e.message}")
            }
        }
    }
}