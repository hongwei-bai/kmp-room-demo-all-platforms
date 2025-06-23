package com.example.kmpdemo.di

import org.koin.dsl.module

val platformModule = module {
    // Provide Factory (platform-specific)
    single<Factory> { Factory() }
}