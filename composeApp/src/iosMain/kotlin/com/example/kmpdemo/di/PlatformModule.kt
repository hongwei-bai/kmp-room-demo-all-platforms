package com.example.kmpdemo.di

import org.koin.dsl.module

val platformModule = module {
    single { Factory() } // iOS version of Factory
}