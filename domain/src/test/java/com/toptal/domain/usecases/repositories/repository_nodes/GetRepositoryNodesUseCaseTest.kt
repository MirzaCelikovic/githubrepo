package com.toptal.domain.usecases.repositories.repository_nodes

import com.toptal.domain.model.repository_details.RepositoryDetails
import com.toptal.domain.model.repository_nodes.RepositoryNode
import com.toptal.domain.usecases.repositories.RepositoryClient
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetRepositoryNodesUseCaseTest {

    private lateinit var getRepositories: GetRepositoryNodesUseCase

    private val repos = listOf(
        RepositoryNode("A", "desc a", "url", 234),
        RepositoryNode("b", "desc b", "url", 92340),
        RepositoryNode("C", "desc c", "url", 4356),
        RepositoryNode("d", "desc d", "url", 902)
    ).shuffled()

    private val sortedRepos = repos.sortedBy { it.name.lowercase() }

    @Before
    fun setUp() {
        getRepositories = GetRepositoryNodesUseCase(object: RepositoryClient {
            override suspend fun getRepositories() = repos

            override suspend fun getRepositoryDetails(repositoryName: String): RepositoryDetails {
                TODO("Not yet implemented")
            }
        })
    }


    @Test
    fun `when requesting repositories, GetRepositoriesNotedUseCase returns repositories sorted alphabetically`() = runTest {
        assertEquals("Not sorted alphabetically", sortedRepos, getRepositories())
    }
}