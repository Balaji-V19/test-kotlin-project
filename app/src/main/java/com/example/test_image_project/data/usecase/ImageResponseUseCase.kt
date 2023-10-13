package com.example.test_image_project.data.usecase

import android.content.Context
import android.util.Log
import com.example.test_image_project.data.repository.ImageRepository
import com.example.test_image_project.model.CreateOrEditImageRequest
import com.example.test_image_project.model.ImageModelResponse
import com.example.test_image_project.network.Either

class ImageResponseUseCase(
    private val imageRepository: ImageRepository,
) {

    suspend fun getImageDetails(): Either<List<ImageModelResponse>> {
        return imageRepository.getImages()
    }

    suspend fun addNewImage(request: CreateOrEditImageRequest): Either<ImageModelResponse> {
        Log.d("Not a valid", "print all: ${request.imageURL} and ${request.name} and ${request}")
        if (request.imageURL.isNullOrBlank()) {
            return Either.failure(Throwable("ImageUrl is empty"))
        }
        if (request.name.isBlank()) {
            return Either.failure(Throwable("Name is empty"))
        }
        if (!isLink(request.imageURL)) {
            return Either.failure(Throwable("ImageUrl is not a valid Url"))
        }
        return imageRepository.addNewImage(request)
    }

    val isLink: (String) -> Boolean = { input ->
        val urlPattern = """^(http|https|ftp)://[^\s/$.?#].[^\s]*$""".toRegex(RegexOption.IGNORE_CASE)
        urlPattern.matches(input)
    }
}