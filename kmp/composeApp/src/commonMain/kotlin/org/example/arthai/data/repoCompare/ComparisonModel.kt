package org.example.arthai.data.repoCompare


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ComparisonModel(
    @SerialName("better_for_beginners")
    val betterForBeginners: String = "",
    @SerialName("comparison_summary")
    val comparisonSummary: String = "",
    @SerialName("more_scalable")
    val moreScalable: String = "",
    @SerialName("overall_winner")
    val overallWinner: String = "",
    @SerialName("stronger_community")
    val strongerCommunity: String = ""
)