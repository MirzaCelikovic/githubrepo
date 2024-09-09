package com.toptal.githubrepo.domain.usecases.repository_nodes

import com.toptal.githubrepo.domain.model.repository_nodes.RepositoryNode

interface RepositoryNodeClient {
    suspend fun getRepositories(): List<RepositoryNode>
}