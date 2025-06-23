package com.example.kmpdemo.di

import org.koin.dsl.module
import android.content.Context
import com.example.kmpdemo.database.AppDatabase
import com.example.kmpdemo.database.DataRepository
import com.example.kmpdemo.network.FruittieApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

fun platformModule(context: Context) = module {
    // Provide Factory (platform-specific)
    single<Factory> { Factory(get()) } // `get()` = Android Application
}