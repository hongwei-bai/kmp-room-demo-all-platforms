package com.example.kmpdemo.di

import com.example.kmpdemo.database.AppDatabase
import com.example.kmpdemo.database.DataRepository
import com.example.kmpdemo.network.FruittieApi
import com.example.kmpdemo.viewmodel.HomeViewModel
import com.example.kmpdemo.viewmodel.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

val commonModule = module {
    // Provide FruittieApi
    single<FruittieApi> { get<Factory>().createApi() }

    // Provide AppDatabase
    single<AppDatabase> { get<Factory>().createRoomDatabase() }

    // Provide CoroutineScope
    single {
        CoroutineScope(Dispatchers.Default + SupervisorJob())
    }

    single { DataRepository(get(), get(), get()) }
    factory { MainViewModel(get()) }
    factory { HomeViewModel(get()) }
}