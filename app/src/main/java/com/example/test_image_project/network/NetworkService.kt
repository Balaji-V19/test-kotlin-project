package com.example.test_image_project.network
import com.example.test_image_project.network.model.ApiImageModel

class NetworkService(
    private val apiService: ApiService,
) {

    suspend fun getImages(): Either<List<ApiImageModel>> {
        return try {
            val response = apiService.getImages()
            val body = response.body()

            if (!response.isSuccessful
                || body.isNullOrEmpty()
            ) {
                return Either.failure(Throwable("Something went wrong"))
            }

            Either.success(body)
        } catch (error: Throwable) {
            Either.failure(error)
        }
    }

}