package org.example.arthai.data.repoAnalyze

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepoAnalyzeDto(
    @SerialName("repo_link")
    val repoLink: String
)