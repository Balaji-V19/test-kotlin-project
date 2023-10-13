package com.example.test_image_project.network
import com.example.test_image_project.model.EditImageRequest
import com.example.test_image_project.model.ImageModelResponse
import com.example.test_image_project.network.model.ApiImageModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("images")
    suspend fun getImages(): Response<List<ApiImageModel>>

    @PUT("edit/{id}")
    suspend fun editImage(@Path("id") id: String, @Body request: EditImageRequest): Response<ImageModelResponse>

    @POST("images")
    suspend fun addImage(@Body request: EditImageRequest): Response<ImageModelResponse>

    @DELETE("delete/{id}")
    suspend fun deleteImage(@Path("id") id: String): Response<ImageModelResponse>

//    companion object {
//        fun create(): ApiService {
//            val retrofit = Retrofit.Builder()
//                .baseUrl("https://6526b87d917d673fd76ce2e3.mockapi.io/api/v1/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//            return retrofit.create(ApiService::class.java)
//        }
//    }
}