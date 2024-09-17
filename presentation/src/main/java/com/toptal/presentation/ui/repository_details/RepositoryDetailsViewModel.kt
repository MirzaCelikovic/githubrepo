package com.toptal.presentation.ui.repository_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toptal.domain.model.repository_details.RepositoryDetails
import com.toptal.domain.usecases.repository_details.GetRepositoryDetailsUseCase
import com.toptal.domain.usecases.repository_nodes.AddStarUseCase
import com.toptal.domain.usecases.repository_nodes.RemoveStarUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryDetailsViewModel
@Inject constructor(
    val getRepositoryDetails: GetRepositoryDetailsUseCase,
    val addStar: AddStarUseCase,
    val removeStar: RemoveStarUseCase
): ViewModel() {
    private val _state = MutableStateFlow(RepositoryDetailsState())
    val state = _state.asStateFlow()

    fun fetchRepoDetails(repositoryName: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            _state.update { it.copy(
                repoDetails = getRepositoryDetails(repositoryName),
                isLoading = false
            ) }
        }
    }

    fun toggleStar() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    stared = if (it.stared) {
                        removeStar.invoke(state.value.repoDetails.id)
                    } else {
                        addStar.invoke(state.value.repoDetails.id)
                    }
                )
            }
        }
    }

    data class RepositoryDetailsState(
        val repoDetails: RepositoryDetails = RepositoryDetails.empty(),
        val isLoading: Boolean = false,
        val stared: Boolean = false
    )
}