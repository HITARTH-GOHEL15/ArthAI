package org.example.arthai.ui.repoAnalyze

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material.icons.filled.TipsAndUpdates
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.arthai.core.ErrorRed
import org.example.arthai.core.GreenBorder
import org.example.arthai.core.GreenPrimary
import org.example.arthai.core.GreenSecondary
import org.example.arthai.core.NightBlack
import org.example.arthai.core.NightCharcoal
import org.example.arthai.core.NightSurface
import org.example.arthai.core.SuccessGreen
import org.example.arthai.core.TextLightGrey
import org.example.arthai.core.TextMediumGrey
import org.example.arthai.core.TextWhite
import org.example.arthai.core.WarningYellow
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoAnalyzeRoute(
    paddingValues: PaddingValues
) {
    val viewModel: RepoAnalyzeViewModel = koinInject()
    val state by viewModel.state.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val scope = rememberCoroutineScope()

    var repositoryUrl by remember { mutableStateOf("") }
    var showResults by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = NightBlack,
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(NightBlack),
            contentPadding = PaddingValues(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Logo and Title
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(bottom = 32.dp),

                ) {
                    // DevLens Logo
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(GreenPrimary),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = ">_",
                                color = TextWhite,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "ArthAI",
                            color = TextWhite,
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Text(
                        text = "Understand any repository in seconds",
                        color = TextLightGrey,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
            // Input Section
            item {
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = NightSurface
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            modifier = Modifier.padding(bottom = 16.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = GreenSecondary,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = "Transform your repository analysis with AI-powered insights",
                                color = TextLightGrey,
                                fontSize = 13.sp,
                                lineHeight = 18.sp
                            )
                        }

                        Text(
                            text = "Repository URL",
                            color = TextWhite,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        OutlinedTextField(
                            value = repositoryUrl,
                            onValueChange = { repositoryUrl = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp)),
                            placeholder = {
                                Text(
                                    text = "github.com/facebook/react",
                                    color = TextMediumGrey,
                                    fontSize = 14.sp
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = TextWhite,
                                unfocusedTextColor = TextLightGrey,
                                focusedContainerColor = NightBlack,
                                unfocusedContainerColor = NightBlack,
                                focusedBorderColor = GreenBorder,
                                unfocusedBorderColor = NightCharcoal
                            ),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = {
                                if (repositoryUrl.isNotEmpty()) {
                                    viewModel.resetState()
                                    viewModel.repoAnalyze(repositoryUrl)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GreenPrimary
                            ),
                            shape = RoundedCornerShape(12.dp),
                            enabled = repositoryUrl.isNotEmpty() && !loading
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                if (loading) {
                                    CircularProgressIndicator(
                                        color = Color.White,
                                        modifier = Modifier.size(20.dp),
                                        strokeWidth = 2.dp
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                }
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (loading) "Analyzing..." else "Analyze Repository",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }


            // Results Section
            if (state != null && !loading) {

                val repoData = state!!

                item {
                    Text(
                        text = "Analysis Results",
                        color = TextWhite,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                    )
                }

                if (repoData.status == "success") {

                    val analysis = repoData.analysis
                    val githubData = repoData.githubData

                    // 🔥 AI Score Card
                    analysis.let { analysisData ->
                        item {
                            ArthAIScoreCard(
                                score = analysisData.aiScore,
                                confidence = analysisData.confidenceScore
                            )
                        }
                    }

                    // 🔥 GitHub Stats
                    githubData.let { data ->
                        item {
                            ArthAIGithubStats(data)
                        }
                    }

                    // 🔥 Summary
                    analysis.summary.takeIf { it.isNotEmpty() }?.let { summary ->
                        item {
                            ArthAISection(
                                title = "Summary",
                                icon = Icons.Default.Description,
                                iconColor = Color(0xFF90CAF9)
                            ) {
                                Text(
                                    text = summary,
                                    color = TextLightGrey,
                                    fontSize = 14.sp,
                                    lineHeight = 20.sp
                                )
                            }
                        }
                    }

                    // 🔥 Target Audience & Complexity
                    analysis.let { analysisData ->
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {

                                if (analysisData.targetAudience.isNotEmpty()) {
                                    Box(modifier = Modifier.weight(1f)) {
                                        ArthAIMetricCard(
                                            title = "Target Audience",
                                            value = analysisData.targetAudience,
                                            icon = Icons.Default.People,
                                            color = Color(0xFF81C784)
                                        )
                                    }
                                }

                                if (analysisData.complexity.isNotEmpty()) {
                                    Box(modifier = Modifier.weight(1f)) {
                                        ArthAIMetricCard(
                                            title = "Complexity",
                                            value = analysisData.complexity,
                                            icon = Icons.Default.Speed,
                                            color = getComplexityColor(analysisData.complexity)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // 🔥 Tech Stack
                    analysis.techStack?.takeIf { it.isNotEmpty() }?.let { techStack ->
                        item {
                            ArthAISection(
                                title = "Tech Stack",
                                icon = Icons.Default.Code,
                                iconColor = Color(0xFFBB86FC)
                            ) {
                                techStack.forEach { tech ->
                                    ArthAIBulletPoint(
                                        text = tech,
                                        icon = "•",
                                        color = GreenSecondary
                                    )
                                }
                            }
                        }
                    }

                    // 🔥 Use Cases
                    analysis.useCases?.takeIf { it.isNotEmpty() }?.let { useCases ->
                        item {
                            ArthAISection(
                                title = "Use Cases",
                                icon = Icons.Default.Lightbulb,
                                iconColor = Color(0xFFFFB74D)
                            ) {
                                useCases.forEach { useCase ->
                                    ArthAIBulletPoint(
                                        text = useCase,
                                        icon = "💡",
                                        color = SuccessGreen
                                    )
                                }
                            }
                        }
                    }

                    // 🔥 Strengths
                    analysis.strengths?.takeIf { it.isNotEmpty() }?.let { strengths ->
                        item {
                            ArthAISection(
                                title = "Strengths",
                                icon = Icons.Default.CheckCircle,
                                iconColor = SuccessGreen
                            ) {
                                strengths.forEach { strength ->
                                    ArthAIBulletPoint(
                                        text = strength,
                                        icon = "✓",
                                        color = SuccessGreen
                                    )
                                }
                            }
                        }
                    }

                    // 🔥 Improvement Suggestions
                    analysis.improvementSuggestions?.takeIf { it.isNotEmpty() }?.let { suggestions ->
                        item {
                            ArthAISection(
                                title = "Improvement Suggestions",
                                icon = Icons.Default.TipsAndUpdates,
                                iconColor = WarningYellow
                            ) {
                                suggestions.forEach { suggestion ->
                                    ArthAIBulletPoint(
                                        text = suggestion,
                                        icon = "⚠",
                                        color = WarningYellow
                                    )
                                }
                            }
                        }
                    }

                    // 🔥 Community & Maintenance
                    analysis.let { analysisData ->
                        item {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {

                                if (analysisData.communityStrength.isNotEmpty()) {
                                    Box(modifier = Modifier.weight(1f)) {
                                        ArthAIMetricCard(
                                            title = "Community",
                                            value = analysisData.communityStrength,
                                            icon = Icons.Default.Groups,
                                            color = getRiskColor(analysisData.communityStrength, inverse = true)
                                        )
                                    }
                                }

                                if (analysisData.maintenanceRisk.isNotEmpty()) {
                                    Box(modifier = Modifier.weight(1f)) {
                                        ArthAIMetricCard(
                                            title = "Maintenance Risk",
                                            value = analysisData.maintenanceRisk,
                                            icon = Icons.Default.Warning,
                                            color = getRiskColor(analysisData.maintenanceRisk)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // 🔥 Topics
                    githubData.topics.takeIf { it.isNotEmpty() }?.let { topics ->
                        item {
                            ArthAISection(
                                title = "Topics",
                                icon = Icons.Default.Tag,
                                iconColor = Color(0xFF4DD0E1)
                            ) {
                                topics.forEach { topic ->
                                    Surface(
                                        shape = RoundedCornerShape(16.dp),
                                        color = GreenSecondary.copy(alpha = 0.15f)
                                    ) {
                                        Text(
                                            text = topic,
                                            color = GreenSecondary,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Medium,
                                            modifier = Modifier.padding(
                                                horizontal = 12.dp,
                                                vertical = 6.dp
                                            )
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(8.dp))
                                }
                            }
                        }
                    }

                } else {
                    item {
                        ErrorCard("Failed to analyze repository. Please try again.")
                    }
                }
                }
            }
        }
    }


@Composable
fun ArthAIScoreCard(score: Int, confidence: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = NightSurface
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            GreenPrimary.copy(alpha = 0.3f),
                            GreenSecondary.copy(alpha = 0.05f)
                        )
                    )
                )
                .padding(24.dp)
        ) {
            Column {
                Text(
                    text = "AI Score",
                    color = TextMediumGrey,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$score%",
                    color = GreenSecondary,
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { score / 100f },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = GreenSecondary,
                    trackColor = NightCharcoal,
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Confidence: $confidence",
                    color = TextLightGrey,
                    fontSize = 13.sp
                )
            }
        }
    }
}

@Composable
fun ArthAIGithubStats(githubData: org.example.arthai.data.repoAnalyze.GithubDataModel) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = NightSurface
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = githubData.name,
                color = TextWhite,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            if (!githubData.description.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                githubData.description?.let {
                    Text(
                        text = it,
                        color = TextMediumGrey,
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ArthAIStatItem("⭐", githubData.stars.toString(), "Stars")
                ArthAIStatItem("🔀", githubData.forks.toString(), "Forks")
                ArthAIStatItem("🐛", githubData.openIssues.toString(), "Issues")
            }

            if (!githubData.language.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                HorizontalDivider(color = NightCharcoal, thickness = 1.dp)
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Primary Language:",
                        color = TextMediumGrey,
                        fontSize = 13.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = GreenPrimary.copy(alpha = 0.2f)
                    ) {
                        githubData.language?.let {
                            Text(
                                text = it,
                                color = GreenSecondary,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ArthAIStatItem(icon: String, value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = icon,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            color = TextWhite,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            color = TextMediumGrey,
            fontSize = 12.sp
        )
    }
}

@Composable
fun ArthAISection(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color,
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = NightSurface
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = iconColor,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = title,
                    color = TextWhite,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            content()
        }
    }
}

@Composable
fun ArthAIMetricCard(
    title: String,
    value: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = NightSurface
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = color,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                color = TextMediumGrey,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                color = TextWhite,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ArthAIBulletPoint(text: String, icon: String, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text(
            text = icon,
            color = color,
            fontSize = 14.sp,
            modifier = Modifier.padding(end = 12.dp)
        )
        Text(
            text = text,
            color = TextLightGrey,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ErrorCard(message: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = ErrorRed.copy(alpha = 0.1f)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Error,
                contentDescription = "Error",
                tint = ErrorRed,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = message,
                color = ErrorRed,
                fontSize = 14.sp
            )
        }
    }
}

fun getComplexityColor(complexity: String): Color {
    return when (complexity.lowercase()) {
        "beginner" -> SuccessGreen
        "intermediate" -> WarningYellow
        "advanced" -> ErrorRed
        else -> TextMediumGrey
    }
}

fun getRiskColor(level: String, inverse: Boolean = false): Color {
    val color = when (level.lowercase()) {
        "low" -> SuccessGreen
        "medium" -> WarningYellow
        "high" -> ErrorRed
        else -> TextMediumGrey
    }

    return if (inverse) {
        when (color) {
            ErrorRed -> SuccessGreen
            SuccessGreen -> ErrorRed
            else -> color
        }
    } else color
}