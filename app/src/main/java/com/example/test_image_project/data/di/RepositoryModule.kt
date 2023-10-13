package com.example.test_image_project.data.di

import com.example.test_image_project.data.repository.ImageRepository
import com.example.test_image_project.data.repository.ImageRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<ImageRepository> { ImageRepositoryImpl(get(),get()) }

}