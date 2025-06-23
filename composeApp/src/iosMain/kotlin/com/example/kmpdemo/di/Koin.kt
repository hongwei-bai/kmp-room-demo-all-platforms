package com.example.kmpdemo.di

import org.koin.core.context.startKoin

fun initKoinIos() {
    startKoin {
        modules(platformModule, commonModule)
    }
}