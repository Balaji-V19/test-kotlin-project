package com.example.test_image_project.data.di

import com.example.test_image_project.data.mapper.ImageResponseMapper
import org.koin.dsl.module


val mapperModule = module {
    single { ImageResponseMapper() }
}