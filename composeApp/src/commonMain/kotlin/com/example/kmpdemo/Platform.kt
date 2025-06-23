package com.example.kmpdemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform