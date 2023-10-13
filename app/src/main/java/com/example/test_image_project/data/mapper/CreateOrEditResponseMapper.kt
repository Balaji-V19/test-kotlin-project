package com.example.test_image_project.data.mapper

import com.example.test_image_project.model.ImageModelResponse
import com.example.test_image_project.network.model.ApiImageModel

class CreateOrEditResponseMapper : Mapper<ApiImageModel, ImageModelResponse> {

    override fun toDomain(model: ApiImageModel): ImageModelResponse {
        return ImageModelResponse(
                imageUrl = model.imageUrl,
                createdAt = model.createdAt,
                name = model.name,
                id = model.id
            )
    }

}