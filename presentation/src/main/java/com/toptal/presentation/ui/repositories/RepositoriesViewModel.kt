package com.toptal.presentation.ui.repositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toptal.domain.model.repository_nodes.RepositoryNode
import com.toptal.domain.usecases.repository_nodes.GetRepositoryNodesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel
@Inject constructor(
    private val getRepositoryNodesUseCase: GetRepositoryNodesUseCase
): ViewModel() {

    private val _state = MutableStateFlow(RepositoriesState())
    val state = _state.asStateFlow()

    init {
        fetchRepos()
    }

    private fun fetchRepos() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            _state.update {
                it.copy(
                    repos = getRepositoryNodesUseCase(),
                    isLoading = false
                )
            }
        }
    }

    data class RepositoriesState(
        val repos: List<RepositoryNode> = emptyList(),
        val isLoading: Boolean = false
    )
}