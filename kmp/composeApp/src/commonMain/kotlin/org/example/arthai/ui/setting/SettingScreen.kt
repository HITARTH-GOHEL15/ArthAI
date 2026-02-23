package org.example.arthai.ui.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.arthai.core.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    username: String = "@dev_pro",
    email: String = "dev@example.com",
    subscriptionPlan: String = "Pro Developer",
    isGitHubConnected: Boolean = true,
    currentTheme: String = "Dark",
    defaultBranch: String = "main",
    contextDepth: String = "High (10k tokens)",
    advancedAnalysisEnabled: Boolean = true,
    onBackClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onEmailClick: () -> Unit = {},
    onSubscriptionClick: () -> Unit = {},
    onThemeClick: () -> Unit = {},
    onDefaultBranchClick: () -> Unit = {},
    onAdvancedAnalysisToggle: (Boolean) -> Unit = {},
    onContextDepthClick: () -> Unit = {},
    onSignOutClick: () -> Unit = {},
    paddingValues: PaddingValues
) {
    var advancedAnalysis by remember { mutableStateOf(advancedAnalysisEnabled) }

    Scaffold(
        containerColor = NightBlack
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(vertical = 24.dp)
        ) {
            // Profile Header
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Profile Avatar with checkmark
                    Box(
                        contentAlignment = Alignment.BottomEnd
                    ) {
                        Box(
                            modifier = Modifier
                                .size(140.dp)
                                .border(
                                    width = 3.dp,
                                    color = GreenSecondary,
                                    shape = CircleShape
                                )
                                .padding(4.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE8F5E9))
                                .clickable(onClick = onProfileClick)
                        ) {
                            // Placeholder for profile image
                            // You can replace this with AsyncImage for real images
                            Text(
                                text = "👨‍💻",
                                fontSize = 60.sp,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }

                        // Verified checkmark
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(GreenSecondary),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Verified",
                                tint = NightBlack,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Username
                    Text(
                        text = username,
                        color = TextWhite,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // GitHub Connected Badge
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = NightSurface,
                        modifier = Modifier.clickable(onClick = onProfileClick)
                    ) {
                        Row(
                            modifier = Modifier.padding(
                                horizontal = 20.dp,
                                vertical = 10.dp
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(if (isGitHubConnected) GreenSecondary else TextMediumGrey)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (isGitHubConnected) "CONNECTED TO GITHUB" else "NOT CONNECTED",
                                color = if (isGitHubConnected) GreenSecondary else TextMediumGrey,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 0.5.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }

            // ACCOUNT SETTINGS Section
            item {
                SectionHeader("ACCOUNT SETTINGS")
            }

            item {
                SettingsCard {
                    SettingsRow(
                        icon = Icons.Default.Email,
                        iconTint = Color(0xFF90CAF9),
                        title = "Email",
                        subtitle = "Primary contact email",
                        trailing = email,
                        onClick = onEmailClick
                    )

                    Divider(
                        color = NightCharcoal,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    SettingsRow(
                        icon = Icons.Default.Verified,
                        iconTint = GreenSecondary,
                        title = "Subscription Plan",
                        subtitle = "Billed monthly",
                        trailing = subscriptionPlan,
                        showChevron = true,
                        onClick = onSubscriptionClick
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            // PREFERENCES Section
            item {
                SectionHeader("PREFERENCES")
            }

            item {
                SettingsCard {
                    SettingsRow(
                        icon = Icons.Default.DarkMode,
                        iconTint = Color(0xFFBB86FC),
                        title = "Theme",
                        subtitle = null,
                        trailing = currentTheme,
                        onClick = onThemeClick
                    )

                    Divider(
                        color = NightCharcoal,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    SettingsRow(
                        icon = Icons.Default.AccountTree,
                        iconTint = Color(0xFF81C784),
                        title = "Default Branch",
                        subtitle = null,
                        trailing = defaultBranch,
                        showChevron = true,
                        onClick = onDefaultBranchClick
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            // AI CONFIGURATION Section
            item {
                Row(
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "AI CONFIGURATION",
                        color = TextMediumGrey,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Surface(
                        shape = RoundedCornerShape(4.dp),
                        color = GreenSecondary
                    ) {
                        Text(
                            text = "BETA",
                            color = NightBlack,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }

            item {
                SettingsCard {
                    SettingsToggleRow(
                        icon = Icons.Default.Psychology,
                        iconTint = Color(0xFF4DD0E1),
                        title = "Advanced Analysis",
                        subtitle = "Deep structural repository audit",
                        isEnabled = advancedAnalysis,
                        onToggle = { enabled ->
                            advancedAnalysis = enabled
                            onAdvancedAnalysisToggle(enabled)
                        }
                    )

                    Divider(
                        color = NightCharcoal,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    SettingsRow(
                        icon = Icons.Default.Layers,
                        iconTint = Color(0xFFFFB74D),
                        title = "Context Depth",
                        subtitle = null,
                        trailing = contextDepth,
                        showChevron = true,
                        onClick = onContextDepthClick
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))
            }

            // Sign Out Button
            item {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .clickable(onClick = onSignOutClick),
                    shape = RoundedCornerShape(12.dp),
                    color = NightSurface
                ) {
                    Row(
                        modifier = Modifier.padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "Sign Out",
                            tint = ErrorRed,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Sign Out",
                            color = ErrorRed,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            // Footer
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Computer,
                            contentDescription = null,
                            tint = GreenSecondary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "GITAI ENGINE",
                            color = GreenSecondary,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "v2.4.12-stable • Build 8429",
                        color = TextMediumGrey,
                        fontSize = 11.sp
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        color = TextMediumGrey,
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.sp,
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
    )
}

@Composable
fun SettingsCard(
    content: @Composable () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(16.dp),
        color = NightSurface
    ) {
        Column {
            content()
        }
    }
}

@Composable
fun SettingsRow(
    icon: ImageVector,
    iconTint: Color,
    title: String,
    subtitle: String?,
    trailing: String,
    showChevron: Boolean = false,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = iconTint,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                color = TextWhite,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            subtitle?.let {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = it,
                    color = TextMediumGrey,
                    fontSize = 13.sp
                )
            }
        }

        Text(
            text = trailing,
            color = TextLightGrey,
            fontSize = 14.sp
        )

        if (showChevron) {
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = TextMediumGrey,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun SettingsToggleRow(
    icon: ImageVector,
    iconTint: Color,
    title: String,
    subtitle: String?,
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = iconTint,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                color = TextWhite,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
            subtitle?.let {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = it,
                    color = TextMediumGrey,
                    fontSize = 13.sp
                )
            }
        }

        Switch(
            checked = isEnabled,
            onCheckedChange = onToggle,
            colors = SwitchDefaults.colors(
                checkedThumbColor = TextWhite,
                checkedTrackColor = GreenSecondary,
                uncheckedThumbColor = TextMediumGrey,
                uncheckedTrackColor = NightCharcoal
            )
        )
    }
}