package org.example.arthai.data

import org.example.arthai.data.repoAnalyze.RepoAnalyzeModel
import org.example.arthai.data.repoCompare.RepoCompareModel

class ArthRepository(
    private val api: ArthAPI
) {
    suspend fun repoAnalyze(repoLink: String): RepoAnalyzeModel {
        return api.repoAnalyze(repoLink)
    }
    suspend fun repoCompare(repoLink1: String, repoLink2: String): RepoCompareModel {
        return api.repoCompare(repoLink1,repoLink2)
    }
}