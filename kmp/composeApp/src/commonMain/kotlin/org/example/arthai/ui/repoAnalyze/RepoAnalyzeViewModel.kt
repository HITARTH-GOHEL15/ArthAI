package org.example.arthai.ui.repoAnalyze

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.arthai.data.ArthRepository
import org.example.arthai.data.repoAnalyze.AnalysisModel
import org.example.arthai.data.repoAnalyze.GithubDataModel
import org.example.arthai.data.repoAnalyze.RepoAnalyzeModel

class RepoAnalyzeViewModel(
    private val repository: ArthRepository
) : ViewModel() {
    private val _state = MutableStateFlow<RepoAnalyzeModel?>(null)
    val state: StateFlow<RepoAnalyzeModel?> = _state

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun repoAnalyze(repoLink: String) {
        viewModelScope.launch {
            _loading.value = true
            println("🔍 Starting analysis for: $repoLink")

            try {
                _state.value = repository.repoAnalyze(repoLink)
                println("✅ Analysis complete!")
            } catch (e: Exception) {
                println("❌ Error: ${e.message}")
                _state.value = RepoAnalyzeModel(
                    repository = "",
                    status = "error",
                    githubData = GithubDataModel(),
                    analysis = AnalysisModel()
                )
            }
            _loading.value = false
        }
    }

    fun resetState() {  // ⭐ ADD THIS
        _state.value = null
        _loading.value = false
    }
}