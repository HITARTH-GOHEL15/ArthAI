package org.example.arthai.ui.repo_comparison

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
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.CompareArrows
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.example.arthai.core.ErrorRed
import org.example.arthai.core.GreenPrimary
import org.example.arthai.core.GreenSecondary
import org.example.arthai.core.NightBlack
import org.example.arthai.core.NightCharcoal
import org.example.arthai.core.NightSurface
import org.example.arthai.core.SuccessGreen
import org.example.arthai.core.TextLightGrey
import org.example.arthai.core.TextMediumGrey
import org.example.arthai.core.TextWhite
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepoCompareRoute(
    paddingValues: PaddingValues
) {
    val viewModel: RepoCompareViewModel = koinInject()
    val state by viewModel.state.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val scope = rememberCoroutineScope()

    var repo1Url by remember { mutableStateOf("") }
    var repo2Url by remember { mutableStateOf("") }
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
                                text = "Compare two repositories side by side with AI insights",
                                color = TextLightGrey,
                                fontSize = 13.sp,
                                lineHeight = 18.sp
                            )
                        }

                        // Repository 1
                        Text(
                            text = "Repository 1",
                            color = TextWhite,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        OutlinedTextField(
                            value = repo1Url,
                            onValueChange = { repo1Url = it },
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
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Link,
                                    contentDescription = "Link",
                                    tint = Color(0xFF90CAF9)
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = TextWhite,
                                unfocusedTextColor = TextLightGrey,
                                focusedContainerColor = NightBlack,
                                unfocusedContainerColor = NightBlack,
                                focusedBorderColor = Color(0xFF90CAF9),
                                unfocusedBorderColor = NightCharcoal
                            ),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        // VS Badge
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Surface(
                                shape = RoundedCornerShape(20.dp),
                                color = GreenSecondary
                            ) {
                                Text(
                                    text = "VS",
                                    color = NightBlack,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Repository 2
                        Text(
                            text = "Repository 2",
                            color = TextWhite,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(bottom = 12.dp)
                        )

                        OutlinedTextField(
                            value = repo2Url,
                            onValueChange = { repo2Url = it },
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp)),
                            placeholder = {
                                Text(
                                    text = "github.com/vuejs/vue",
                                    color = TextMediumGrey,
                                    fontSize = 14.sp
                                )
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Link,
                                    contentDescription = "Link",
                                    tint = Color(0xFFBB86FC)
                                )
                            },
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = TextWhite,
                                unfocusedTextColor = TextLightGrey,
                                focusedContainerColor = NightBlack,
                                unfocusedContainerColor = NightBlack,
                                focusedBorderColor = Color(0xFFBB86FC),
                                unfocusedBorderColor = NightCharcoal
                            ),
                            shape = RoundedCornerShape(12.dp),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Button(
                            onClick = {
                                if (repo1Url.isNotEmpty() && repo2Url.isNotEmpty()) {
                                    showResults = false
                                    scope.launch {
                                        delay(100)
                                        viewModel.repoCompare(repo1Url, repo2Url)
                                        showResults = true
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = GreenPrimary
                            ),
                            shape = RoundedCornerShape(12.dp),
                            enabled = repo1Url.isNotEmpty() && repo2Url.isNotEmpty() && !loading
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
                                    imageVector = Icons.Default.CompareArrows,
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = if (loading) "Comparing..." else "Compare Repositories",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            // Results Section
            if (showResults && state != null && !loading) {
                item {
                    Text(
                        text = "Comparison Results",
                        color = TextWhite,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                    )
                }

                state?.let { compareData ->
                    if (compareData.status == "success") {
                        val comparison = compareData.comparison

                        // Overall Winner Card
                        item {
                            WinnerCard(
                                winner = comparison.overallWinner,
                                repo1 = repo1Url,
                                repo2 = repo2Url
                            )
                        }

                        // Comparison Summary
                        item {
                            if (comparison.comparisonSummary.isNotEmpty()) {
                                ComparisonSection(
                                    title = "Summary",
                                    icon = Icons.Default.Description,
                                    iconColor = Color(0xFF90CAF9)
                                ) {
                                    Text(
                                        text = comparison.comparisonSummary,
                                        color = TextLightGrey,
                                        fontSize = 14.sp,
                                        lineHeight = 20.sp
                                    )
                                }
                            }
                        }

                        // Comparison Metrics
                        item {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                // Better for Beginners
                                if (comparison.betterForBeginners.isNotEmpty()) {
                                    ComparisonMetricCard(
                                        title = "Better for Beginners",
                                        winner = comparison.betterForBeginners,
                                        icon = Icons.Default.School,
                                        iconColor = Color(0xFF81C784),
                                        repo1 = repo1Url,
                                        repo2 = repo2Url
                                    )
                                }

                                // Stronger Community
                                if (comparison.strongerCommunity.isNotEmpty()) {
                                    ComparisonMetricCard(
                                        title = "Stronger Community",
                                        winner = comparison.strongerCommunity,
                                        icon = Icons.Default.Groups,
                                        iconColor = Color(0xFF64B5F6),
                                        repo1 = repo1Url,
                                        repo2 = repo2Url
                                    )
                                }

                                // More Scalable
                                if (comparison.moreScalable.isNotEmpty()) {
                                    ComparisonMetricCard(
                                        title = "More Scalable",
                                        winner = comparison.moreScalable,
                                        icon = Icons.Default.TrendingUp,
                                        iconColor = Color(0xFFBA68C8),
                                        repo1 = repo1Url,
                                        repo2 = repo2Url
                                    )
                                }
                            }
                        }
                    } else {
                        // Error state
                        item {
                            ErrorCard("Failed to compare repositories. Please try again.")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun WinnerCard(winner: String, repo1: String, repo2: String) {
    val winnerName = extractRepoName(if (winner.contains("1")) repo1 else repo2)

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
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.EmojiEvents,
                    contentDescription = "Winner",
                    tint = GreenSecondary,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Overall Winner",
                    color = TextMediumGrey,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = winnerName,
                    color = GreenSecondary,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = GreenSecondary.copy(alpha = 0.2f)
                ) {
                    Text(
                        text = winner,
                        color = GreenSecondary,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ComparisonSection(
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
fun ComparisonMetricCard(
    title: String,
    winner: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color,
    repo1: String,
    repo2: String
) {
    val winnerName = extractRepoName(if (winner.contains("1")) repo1 else repo2)
    val isRepo1Winner = winner.contains("1")

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = NightSurface
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = title,
                    color = TextWhite,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Repository 1
                ComparisonRepoItem(
                    repoName = extractRepoName(repo1),
                    isWinner = isRepo1Winner,
                    color = Color(0xFF90CAF9)
                )

                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = "vs",
                    tint = TextMediumGrey,
                    modifier = Modifier.size(24.dp)
                )

                // Repository 2
                ComparisonRepoItem(
                    repoName = extractRepoName(repo2),
                    isWinner = !isRepo1Winner,
                    color = Color(0xFFBB86FC)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Winner",
                    tint = SuccessGreen,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Winner: $winnerName",
                    color = SuccessGreen,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun ComparisonRepoItem(
    repoName: String,
    isWinner: Boolean,
    color: Color
) {
    Surface(
        shape = RoundedCornerShape(12.dp),
        color = if (isWinner) SuccessGreen.copy(alpha = 0.15f) else NightCharcoal
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(color)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = repoName,
                color = if (isWinner) SuccessGreen else TextMediumGrey,
                fontSize = 13.sp,
                fontWeight = if (isWinner) FontWeight.Bold else FontWeight.Normal
            )
            if (isWinner) {
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Winner",
                    tint = SuccessGreen,
                    modifier = Modifier.size(14.dp)
                )
            }
        }
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

fun extractRepoName(url: String): String {
    // Extract repository name from URL
    // e.g., "github.com/facebook/react" -> "react"
    return url.substringAfterLast("/").ifEmpty {
        url.substringAfterLast("/", url)
    }
}