package org.example.arthai.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Compare
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.arthai.core.GreenSecondary
import org.example.arthai.core.NightBlack
import org.example.arthai.core.NightCharcoal
import org.example.arthai.core.NightSurface
import org.example.arthai.core.TextMediumGrey
import org.example.arthai.core.TextWhite
import org.example.arthai.ui.repoAnalyze.RepoAnalyzeRoute
import org.example.arthai.ui.repo_comparison.RepoCompareRoute
import org.example.arthai.ui.setting.SettingsScreen

@Composable
fun HomeScreenFixed() {
    var bottomNavigationScreen by rememberSaveable {
        mutableStateOf(BottomNavigationScreens.HomeScreenView)
    }

    Scaffold(
        containerColor = NightBlack,
        bottomBar = {
            NavigationBar(
                containerColor = NightCharcoal,
                contentColor = TextWhite,
                modifier = Modifier.padding(top = 20.dp)
            ) {
                NavigationBarItem(
                    selected = bottomNavigationScreen == BottomNavigationScreens.HomeScreenView,
                    onClick = {
                        bottomNavigationScreen = BottomNavigationScreens.HomeScreenView
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Analytics,
                            contentDescription = "Analysis"
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = GreenSecondary,
                        selectedTextColor = GreenSecondary,
                        unselectedIconColor = TextMediumGrey,
                        unselectedTextColor = TextMediumGrey,
                        indicatorColor = NightSurface
                    )
                )

                NavigationBarItem(
                    selected = bottomNavigationScreen == BottomNavigationScreens.RepoComparisionScreenView,
                    onClick = {
                        bottomNavigationScreen = BottomNavigationScreens.RepoComparisionScreenView
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Compare,
                            contentDescription = "RepoComparision"
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = GreenSecondary,
                        selectedTextColor = GreenSecondary,
                        unselectedIconColor = TextMediumGrey,
                        unselectedTextColor = TextMediumGrey,
                        indicatorColor = NightSurface
                    )
                )

                NavigationBarItem(
                    selected = bottomNavigationScreen == BottomNavigationScreens.SettingScreenView,
                    onClick = {
                        bottomNavigationScreen = BottomNavigationScreens.SettingScreenView
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings"
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = GreenSecondary,
                        selectedTextColor = GreenSecondary,
                        unselectedIconColor = TextMediumGrey,
                        unselectedTextColor = TextMediumGrey,
                        indicatorColor = NightSurface
                    )
                )
            }
        }
    ) { paddingValues ->
        AnimatedContent(
            targetState = bottomNavigationScreen,
            label = "",
            transitionSpec = {
                when (this.targetState) {
                    BottomNavigationScreens.HomeScreenView -> slideInHorizontally { it }
                        .togetherWith(slideOutHorizontally { -it })
                    BottomNavigationScreens.RepoComparisionScreenView -> slideInHorizontally { it }
                        .togetherWith(slideOutHorizontally { -it })
                    BottomNavigationScreens.SettingScreenView -> slideInHorizontally { it }
                        .togetherWith(slideOutHorizontally { -it })
                }
            },
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.background)
        ) { navScreen ->
            when (navScreen) {
                BottomNavigationScreens.HomeScreenView -> {
                    RepoAnalyzeRoute(paddingValues)
                }
                BottomNavigationScreens.RepoComparisionScreenView -> {
                    RepoCompareRoute(paddingValues)
                }
                BottomNavigationScreens.SettingScreenView -> {
                    SettingsScreen(paddingValues = paddingValues)
                }
            }
        }
    }
}


private enum class BottomNavigationScreens {
    HomeScreenView,
    RepoComparisionScreenView,
    SettingScreenView
}