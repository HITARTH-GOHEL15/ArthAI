package org.example.arthai.data.repoAnalyze

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnalysisModel(
    @SerialName("ai_score")
    val aiScore: Int = 0,
    @SerialName("community_strength")
    val communityStrength: String = "",
    @SerialName("complexity")
    val complexity: String = "",
    @SerialName("confidence_score")
    val confidenceScore: String = "",
    @SerialName("improvement_suggestions")
    val improvementSuggestions: List<String> = listOf(),
    @SerialName("maintenance_risk")
    val maintenanceRisk: String = "",
    @SerialName("strengths")
    val strengths: List<String> = listOf(),
    @SerialName("summary")
    val summary: String = "",
    @SerialName("target_audience")
    val targetAudience: String = "",
    @SerialName("tech_stack")
    val techStack: List<String> = listOf(),
    @SerialName("use_cases")
    val useCases: List<String> = listOf()
)