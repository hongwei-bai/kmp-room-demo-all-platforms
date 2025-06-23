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
package com.example.kmpdemo.database

import com.example.kmpdemo.model.Fruittie
import com.example.kmpdemo.network.FruittieApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DataRepository(
    private val api: FruittieApi,
    private var database: AppDatabase,
    private val scope: CoroutineScope,
) {
    fun getData(): Flow<List<Fruittie>> {
        scope.launch {
            if (database.fruittieDao().count() < 1) {
                refreshData()
            }
        }
        return loadData()
    }

    fun loadData(): Flow<List<Fruittie>> = database.fruittieDao().getAllAsFlow()

    suspend fun refreshData() {
        val response = api.getData()
        database.fruittieDao().insert(response.feed)
    }

    // Test Room
    suspend fun getDataTest(): List<Fruittie> {
        return database.fruittieDao().getAll()
    }

    suspend fun insertDataTest(name: String) {
        return database.fruittieDao().insert(
            Fruittie(
                name = name,
                fullName = "Orange full name",
                calories = "200 KJ"
            )
        )
    }

    suspend fun clearAllTest() {
        database.fruittieDao().clearAll()
    }
}
