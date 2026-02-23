package org.example.arthai.data.repoAnalyze

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubDataModel(
    @SerialName("created_at")
    val createdAt: String = "",
    @SerialName("description")
    val description: String? = null,
    @SerialName("forks")
    val forks: Int = 0,
    @SerialName("language")
    val language: String? = null,
    @SerialName("name")
    val name: String = "",
    @SerialName("open_issues")
    val openIssues: Int = 0,
    @SerialName("stars")
    val stars: Int = 0,
    @SerialName("topics")
    val topics: List<String> = listOf(),
    @SerialName("updated_at")
    val updatedAt: String = ""
)