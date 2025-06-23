/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.kmpdemo.di

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.example.kmpdemo.database.AppDatabase
import com.example.kmpdemo.database.DB_FILE_NAME
import com.example.kmpdemo.network.FruittieApi
import io.ktor.client.engine.cio.*
import kotlinx.coroutines.Dispatchers
import java.io.File

actual class Factory {
    actual fun createRoomDatabase(): AppDatabase {
        val dbFile = File(System.getProperty("java.io.tmpdir"), DB_FILE_NAME)
        return Room.databaseBuilder<AppDatabase>(
            name = dbFile.absolutePath,
        ).fallbackToDestructiveMigrationOnDowngrade(
            dropAllTables = false
        ).setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }

    actual fun createApi(): FruittieApi = commonCreateApi(CIO.create())
}
