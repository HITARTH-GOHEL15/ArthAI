package org.example.arthai.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import kotlinx.serialization.json.Json
import io.ktor.serialization.kotlinx.json.json
import org.example.arthai.data.ArthAPI
import org.example.arthai.data.ArthRepository
import org.example.arthai.data.KtorArthAPI
import org.example.arthai.ui.repoAnalyze.RepoAnalyzeViewModel
import org.example.arthai.ui.repo_comparison.RepoCompareViewModel
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModule = module {
    single {
        val json = Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }

        HttpClient {
            install(ContentNegotiation) {
                json(json)
            }

            install(io.ktor.client.plugins.HttpTimeout) {
                requestTimeoutMillis = 60_000
                connectTimeoutMillis = 60_000
                socketTimeoutMillis = 60_000
            }
        }
    }

    single<ArthAPI> {
        KtorArthAPI(get())
    }
    single<ArthRepository> {
        ArthRepository(get())
    }
}

val viewModelModule = module {
    factoryOf(::RepoAnalyzeViewModel)
    factoryOf(::RepoCompareViewModel)
}

fun initKoin() {
    startKoin {
        modules(dataModule , viewModelModule)
    }
}