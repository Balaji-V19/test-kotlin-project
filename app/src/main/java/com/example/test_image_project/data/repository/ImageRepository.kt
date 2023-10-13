package com.example.test_image_project.data.repository
import com.example.test_image_project.data.mapper.CreateOrEditResponseMapper
import com.example.test_image_project.data.mapper.ImageResponseMapper
import com.example.test_image_project.model.CreateOrEditImageRequest
import com.example.test_image_project.model.ImageModelResponse
import com.example.test_image_project.network.Either
import com.example.test_image_project.network.NetworkService
import com.example.test_image_project.network.map


interface ImageRepository {
    suspend fun getImages(): Either<List<ImageModelResponse>>

    suspend fun addNewImage(request: CreateOrEditImageRequest): Either<ImageModelResponse>

    suspend fun editNewImage(imageUrl: String, name: String, id: String): Either<ImageModelResponse>

    suspend fun deleteImage(id: String): Either<ImageModelResponse>
}

class ImageRepositoryImpl(
    private val networkService: NetworkService,
    private val imageMapper: ImageResponseMapper,
    private val createOrEditResponseMapper: CreateOrEditResponseMapper
) : ImageRepository {


    override suspend fun getImages(): Either<List<ImageModelResponse>> {
        return try {
            networkService.getImages().map { imageMapper.toDomain(it) }
        } catch (error: Throwable) {
            Either.failure(error)
        }
    }

    override suspend fun addNewImage(request: CreateOrEditImageRequest): Either<ImageModelResponse> {
        return try {
            networkService.addNewImage(request.imageURL,request.name).map { createOrEditResponseMapper.toDomain(it) }
        } catch (error: Throwable) {
            Either.failure(error)
        }
    }

    override suspend fun editNewImage(imageUrl: String, name: String, id: String): Either<ImageModelResponse> {
        return try {
            networkService.editNewImage(imageUrl,name,id).map { createOrEditResponseMapper.toDomain(it) }
        } catch (error: Throwable) {
            Either.failure(error)
        }
    }

    override suspend fun deleteImage(id: String): Either<ImageModelResponse> {
        return try {
            networkService.deleteImage(id).map { createOrEditResponseMapper.toDomain(it) }
        } catch (error: Throwable) {
            Either.failure(error)
        }
    }

}