package com.toptal.domain.usecases.repositories.repository_nodes

import com.toptal.domain.model.repository_nodes.RepositoryNode
import com.toptal.domain.usecases.repositories.RepositoryClient
import javax.inject.Inject

class GetRepositoryNodesUseCase
@Inject constructor(private val client: RepositoryClient) {
    suspend operator fun invoke(): List<RepositoryNode> {
        return client.getRepositories()
            .sortedWith(compareBy(String.CASE_INSENSITIVE_ORDER) { it.name })
    }
}