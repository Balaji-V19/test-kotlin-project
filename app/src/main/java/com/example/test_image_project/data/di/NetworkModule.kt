package com.example.test_image_project.data.di

import com.example.test_image_project.network.ApiService
import com.example.test_image_project.network.NetworkProvider
import com.example.test_image_project.network.NetworkService
import org.koin.dsl.module

@JvmField
val networkModule = module {

    single { NetworkService(get()) }
    single { NetworkProvider.provideGson() }
    single {
        NetworkProvider.provideApiService(get(), ApiService::class.java)
    }
}