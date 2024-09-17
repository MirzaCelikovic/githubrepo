package com.toptal.domain.usecases.repositories

import com.toptal.domain.model.repository_details.RepositoryDetails
import com.toptal.domain.model.repository_nodes.RepositoryNode

/**
 * Should be called a repository per repository pattern but named client to avoid
 */
interface RepositoryClient {
    suspend fun getRepositories(): List<RepositoryNode>

    suspend fun getRepositoryDetails(repositoryName: String): RepositoryDetails
}