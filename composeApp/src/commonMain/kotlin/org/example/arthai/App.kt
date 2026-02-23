package org.example.arthai

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import org.example.arthai.ui.HomeScreenFixed
import org.example.arthai.ui.repoAnalyze.RepoAnalyzeRoute


@Composable
@Preview
fun App() {
    MaterialTheme {
        HomeScreenFixed()
    }
}