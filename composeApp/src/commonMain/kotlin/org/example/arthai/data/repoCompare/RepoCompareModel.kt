package org.example.arthai.data.repoCompare


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepoCompareModel(
    @SerialName("comparison")
    val comparison: ComparisonModel = ComparisonModel(),
    @SerialName("status")
    val status: String? = ""
)