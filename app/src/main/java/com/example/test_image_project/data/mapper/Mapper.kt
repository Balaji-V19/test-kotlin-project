package com.example.test_image_project.data.mapper

interface Mapper<in Model, out DomainModel> {

    fun toDomain(model: Model): DomainModel
}