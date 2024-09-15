package com.toptal.domain.usecases.repository_nodes

import com.toptal.domain.model.repository_nodes.RepositoryNode

/**
 * Should be called a repository per repository pattern but named client to avoid
 */
interface RepositoryNodeClient {
    suspend fun getRepositories(): List<RepositoryNode>
}