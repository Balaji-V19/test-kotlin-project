package com.example.test_image_project.view_model

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_image_project.data.usecase.ImageResponseUseCase
import com.example.test_image_project.model.ImageModelResponse
import com.example.test_image_project.network.onFailure
import com.example.test_image_project.network.onSuccess
import kotlinx.coroutines.launch

class ImageViewModel(
    private val imageResponseUseCase: ImageResponseUseCase
) : ViewModel() {

    private val _isLoading = ObservableBoolean()
    private val _imageList = MutableLiveData<List<ImageModelResponse>>()
    val imageList: LiveData<List<ImageModelResponse>> = _imageList
    val isLoading: ObservableBoolean = _isLoading

    fun fetchImages() {
        viewModelScope.launch  {
            _isLoading.set(true)
            imageResponseUseCase.getImageDetails()
                .onSuccess {
                    _isLoading.set(false)
                    _imageList.postValue(it)
                }
                .onFailure {
                    _isLoading.set(false)
                    println("error from here $it")
                }
        }
    }
}