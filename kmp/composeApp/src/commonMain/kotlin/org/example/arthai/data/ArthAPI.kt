package org.example.arthai.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import org.example.arthai.data.repoAnalyze.RepoAnalyzeDto
import org.example.arthai.data.repoAnalyze.RepoAnalyzeModel
import org.example.arthai.data.repoCompare.RepoCompareDto
import org.example.arthai.data.repoCompare.RepoCompareModel

interface ArthAPI {
    suspend fun repoAnalyze(repoLink: String): RepoAnalyzeModel
    suspend fun repoCompare(repoLink1: String, repoLink2: String): RepoCompareModel
}

val BASE_URL = "https://arthai-1.onrender.com"

class KtorArthAPI(private val client: HttpClient) : ArthAPI {
    override suspend fun repoAnalyze(repoLink: String): RepoAnalyzeModel {
        return client.post("$BASE_URL/analyze-repo") {
            contentType(ContentType.Application.Json)
            setBody(
                RepoAnalyzeDto(
                    repoLink = repoLink
                )
            )
        }.body()
    }

    override suspend fun repoCompare(
        repoLink1: String,
        repoLink2: String
    ): RepoCompareModel {
        return client.post("$BASE_URL/compare-repos") {
            contentType(ContentType.Application.Json)
            setBody(
                RepoCompareDto(
                    repoLink1,
                    repoLink2
                )
            )
        }.body()
    }
}