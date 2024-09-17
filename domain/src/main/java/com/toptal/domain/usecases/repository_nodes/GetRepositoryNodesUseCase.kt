package com.toptal.domain.usecases.repository_nodes

import com.toptal.domain.model.repository_nodes.RepositoryNode
import javax.inject.Inject

class GetRepositoryNodesUseCase
@Inject constructor(private val client: RepositoryNodeClient) {
    suspend operator fun invoke(): List<RepositoryNode> {
        return client.getRepositories()
    }
}