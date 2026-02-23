package org.example.arthai.data.repoAnalyze

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepoAnalyzeModel(
    @SerialName("analysis")
    val analysis: AnalysisModel = AnalysisModel(),
    @SerialName("github_data")
    val githubData: GithubDataModel = GithubDataModel(),
    @SerialName("repository")
    val repository: String = "",
    @SerialName("status")
    val status: String = ""
)