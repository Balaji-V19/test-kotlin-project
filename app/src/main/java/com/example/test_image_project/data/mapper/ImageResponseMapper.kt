package com.example.test_image_project.data.mapper

import com.example.test_image_project.model.ImageModelResponse
import com.example.test_image_project.network.model.ApiImageModel

class ImageResponseMapper: Mapper<List<ApiImageModel>, List<ImageModelResponse>> {

    override fun toDomain(model: List<ApiImageModel>): List<ImageModelResponse> {
        return model.map {
            ImageModelResponse(
                imageUrl = it.imageUrl,
                createdAt = it.createdAt,
                name = it.name,
                id = it.id
            )
        }
    }

}