package com.example.test_image_project.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal object NetworkProvider {

    fun <T> provideApiService(
        gson: Gson,
        service: Class<T>
    ): T {
        return provideRetrofitBuilder(gson)
            .build()
            .create(service)
    }

    private fun provideRetrofitBuilder(
        gson: Gson
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://6526b87d917d673fd76ce2e3.mockapi.io/api/v1/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }
}