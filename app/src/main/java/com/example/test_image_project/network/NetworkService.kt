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

    suspend fun deleteImage(id: String): Either<ApiImageModel> {
        return try {
            val response = apiService.deleteImage(id)
            val body = response.body()

            if (!response.isSuccessful
                || body==null
            ) {
                return Either.failure(Throwable("Something went wrong"))
            }

            Either.success(body)
        } catch (error: Throwable) {
            Either.failure(error)
        }
    }

    suspend fun addNewImage(imageUrl: String, name: String): Either<ApiImageModel> {
        return try {
            val params = hashMapOf<String, Any>(
                "imageURL" to imageUrl,
                "name" to name,
            )

            val response = apiService.addImage(params)
            val body = response.body()

            if (!response.isSuccessful
                || body == null
            ) {
                return Either.failure(Throwable("Something went wrong"))
            }

            Either.success(body)
        } catch (error: Throwable) {
            Either.failure(error)
        }
    }

    suspend fun editNewImage(imageUrl: String, name: String,id: String): Either<ApiImageModel> {
        return try {
            val params = hashMapOf<String, Any>(
                "imageURL" to imageUrl,
                "name" to name,
            )

            val response = apiService.editImage(id,params)
            val body = response.body()

            if (!response.isSuccessful
                || body == null
            ) {
                return Either.failure(Throwable("Something went wrong"))
            }

            Either.success(body)
        } catch (error: Throwable) {
            Either.failure(error)
        }
    }

}