package com.example.test_image_project.data.di

import com.example.test_image_project.view_model.AddImageViewModel
import com.example.test_image_project.view_model.ImageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { ImageViewModel(get()) }

    viewModel { AddImageViewModel(get()) }
}
