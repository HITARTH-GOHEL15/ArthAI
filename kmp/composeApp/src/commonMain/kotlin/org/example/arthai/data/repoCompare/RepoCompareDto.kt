package org.example.arthai.data.repoCompare

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class RepoCompareDto(
    @SerialName("repo_link_1")
    val repoLink1: String,

    @SerialName("repo_link_2")
    val repoLink2: String
)