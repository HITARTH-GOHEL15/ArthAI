package org.example.arthai

import android.app.Application
import io.ktor.http.ContentType
import org.example.arthai.di.initKoin

class ArthApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}