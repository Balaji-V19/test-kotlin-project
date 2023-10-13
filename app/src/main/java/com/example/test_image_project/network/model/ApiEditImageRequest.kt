package com.example.test_image_project.network.model

import com.google.gson.annotations.SerializedName


data class ApiEditImageRequest(
    @field:SerializedName("imageURL")
    val imageURL: String,
    @field:SerializedName("name")
    val name: String
)
