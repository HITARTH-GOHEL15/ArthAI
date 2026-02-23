package org.example.arthai

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.arthai.di.dataModule
import org.example.arthai.di.viewModelModule
import org.koin.core.context.startKoin

fun main() {
    startKoin {
        modules(dataModule , viewModelModule)
    }
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "ArthAI",
        ) {
            App()
        }
    }
}