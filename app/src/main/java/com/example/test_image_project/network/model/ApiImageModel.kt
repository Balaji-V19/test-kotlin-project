package com.example.test_image_project.network.model

import com.google.gson.annotations.SerializedName

data class ApiImageModel(
    @field:SerializedName("imageURL")
    val imageUrl: String,
    @field:SerializedName("createdAt")
    val createdAt: String,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("id")
    val id: String
)
