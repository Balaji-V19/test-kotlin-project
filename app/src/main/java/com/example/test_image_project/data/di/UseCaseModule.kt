package com.example.test_image_project.data.di

import com.example.test_image_project.data.usecase.ImageResponseUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { ImageResponseUseCase(get()) }
}