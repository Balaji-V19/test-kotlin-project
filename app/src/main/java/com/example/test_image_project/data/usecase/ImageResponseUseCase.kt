package com.example.test_image_project.data.usecase

import com.example.test_image_project.data.repository.ImageRepository
import com.example.test_image_project.model.ImageModelResponse
import com.example.test_image_project.network.Either

class ImageResponseUseCase(
    private val imageRepository: ImageRepository,
) {

    suspend fun getImageDetails(): Either<List<ImageModelResponse>> {
        return imageRepository.getImages()
    }
}