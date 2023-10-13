package com.example.test_image_project.view_model

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_image_project.data.usecase.ImageResponseUseCase
import com.example.test_image_project.model.CreateOrEditImageRequest
import com.example.test_image_project.model.ImageModelResponse
import com.example.test_image_project.network.onFailure
import com.example.test_image_project.network.onSuccess
import kotlinx.coroutines.launch

class AddImageViewModel(
    private val imageResponseUseCase: ImageResponseUseCase
) : ViewModel()  {
    private val _isLoading = ObservableBoolean()
    val showToastMessage = MutableLiveData<String>()
    private val _imageResponse = MutableLiveData<ImageModelResponse>()
    val imageResponse: LiveData<ImageModelResponse> = _imageResponse
    val isLoading: ObservableBoolean = _isLoading

    fun addNewImageAndName(imageUrl: String, name: String) {
        viewModelScope.launch  {
            _isLoading.set(true)
            imageResponseUseCase.addNewImage(CreateOrEditImageRequest(imageUrl, name))
                .onSuccess {
                    _isLoading.set(false)
                    _imageResponse.postValue(it)
                }
                .onFailure {
                    _isLoading.set(false)
                    println("error from here $it")
                    showToastMessage.value = it.message
                }
        }
    }

    fun editImageAndName(imageUrl: String, name: String,id: String) {
        viewModelScope.launch  {
            _isLoading.set(true)
            imageResponseUseCase.editNewImage(imageUrl, name,id)
                .onSuccess {
                    _isLoading.set(false)
                    _imageResponse.postValue(it)
                }
                .onFailure {
                    _isLoading.set(false)
                    println("error from here $it")
                    showToastMessage.value = it.message
                }
        }
    }


}