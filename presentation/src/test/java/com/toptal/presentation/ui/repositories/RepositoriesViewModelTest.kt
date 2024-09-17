package com.toptal.presentation.ui.repositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.toptal.domain.model.repository_details.RepositoryDetails
import com.toptal.domain.model.repository_nodes.RepositoryNode
import com.toptal.domain.usecases.repositories.RepositoryClient
import com.toptal.domain.usecases.repositories.repository_nodes.GetRepositoryNodesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.time.Instant

class RepositoriesViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var repositoryClient: RepositoryClient
    private lateinit var getRepositories: GetRepositoryNodesUseCase
    private lateinit var viewModel: RepositoriesViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
        repositoryClient = object : RepositoryClient {
            override suspend fun getRepositories(): List<RepositoryNode> {
                return listOf(RepositoryNode("", "", "", 0))
            }

            override suspend fun getRepositoryDetails(repositoryName: String): RepositoryDetails {
                return RepositoryDetails.empty()
            }

        }

        getRepositories = GetRepositoryNodesUseCase(repositoryClient)
        viewModel = RepositoriesViewModel(getRepositories)
    }

    @Test
    fun `when view model is created initial state is loading`() {
        assertTrue(viewModel.state.value.isLoading)
        assertTrue(viewModel.state.value.repos.isEmpty())
    }

    @Test
    fun `when fetch is called, is loading is false and list is not empty`() {
        viewModel.fetchRepos()
        assertFalse(viewModel.state.value.isLoading)
        assertTrue(viewModel.state.value.repos.isNotEmpty())
    }
}