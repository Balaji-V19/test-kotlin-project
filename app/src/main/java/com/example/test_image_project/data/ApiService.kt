package com.example.test_image_project.data
import com.example.test_image_project.model.ImageModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("images")
    suspend fun getImages(): Response<List<ImageModel>>

    @PUT("edit/{id}")
    suspend fun editImage(@Path("id") id: String, @Body updatedImage: String): Response<Unit>

    @DELETE("delete/{id}")
    suspend fun deleteImage(@Path("id") id: String): Response<Unit>

    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://6526b87d917d673fd76ce2e3.mockapi.io/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}