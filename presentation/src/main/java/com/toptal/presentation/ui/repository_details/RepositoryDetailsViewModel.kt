package com.toptal.presentation.ui.repository_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toptal.domain.model.repository_details.RepositoryDetails
import com.toptal.domain.usecases.repository_details.GetRepositoryDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryDetailsViewModel
@Inject constructor(
    val getRepositoryDetails: GetRepositoryDetailsUseCase
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

    data class RepositoryDetailsState(
        val repoDetails: RepositoryDetails = RepositoryDetails.empty(),
        val isLoading: Boolean = false
    )
}