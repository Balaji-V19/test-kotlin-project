package com.example.test_image_project.data.repository
import com.example.test_image_project.data.mapper.ImageResponseMapper
import com.example.test_image_project.model.ImageModelResponse
import com.example.test_image_project.network.Either
import com.example.test_image_project.network.NetworkService
import com.example.test_image_project.network.map


interface ImageRepository {
    suspend fun getImages(): Either<List<ImageModelResponse>>
}

class ImageRepositoryImpl(
    private val networkService: NetworkService,
    private val imageMapper: ImageResponseMapper
) : ImageRepository {


    override suspend fun getImages(): Either<List<ImageModelResponse>> {
        return try {
            networkService.getImages().map { imageMapper.toDomain(it) }
        } catch (error: Throwable) {
            Either.failure(error)
        }
    }

}