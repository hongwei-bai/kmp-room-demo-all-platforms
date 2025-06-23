package com.example.kmpdemo

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.kmpdemo.di.commonModule
import com.example.kmpdemo.di.platformModule
import org.koin.core.context.GlobalContext.startKoin
import java.awt.Window

fun main() = application {
    startKoin {
        modules(commonModule, platformModule)
    }

    Window(
        undecorated = false,
        onCloseRequest = ::exitApplication,
        title = "kmp-room-demo",
    ) {
        val awtWindow = this.window
        CompositionLocalProvider(LocalWindow provides awtWindow) {
            App()
        }
    }
}

val LocalWindow = staticCompositionLocalOf<Window> {
    error("LocalWindow not provided")
}