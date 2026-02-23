package org.example.arthai.ui.repo_comparison

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.example.arthai.data.ArthRepository
import org.example.arthai.data.repoCompare.ComparisonModel
import org.example.arthai.data.repoCompare.RepoCompareModel
import org.example.arthai.di.viewModelModule

class RepoCompareViewModel(
    private val repository: ArthRepository
): ViewModel() {
    private val _state = MutableStateFlow<RepoCompareModel?>(null)
    val state: StateFlow<RepoCompareModel?> = _state

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun repoCompare(repoLink1: String, repoLink2: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                _state.value = repository.repoCompare(repoLink1, repoLink2)
            } catch (e: Exception) {
                println("❌ Error: ${e.message}")
                _state.value = RepoCompareModel(
                    comparison = ComparisonModel(),
                    status = "error"
                )
            }
            _loading.value = false
        }
    }
}