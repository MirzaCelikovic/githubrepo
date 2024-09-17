package com.toptal.data.network.model.repository_node

import com.github.RepositoriesQuery
import com.toptal.domain.DomainMapper
import com.toptal.domain.model.repository_nodes.RepositoryNode
import javax.inject.Inject

class RepositoryNodeMapper @Inject constructor():
    DomainMapper<RepositoriesQuery.Node, RepositoryNode> {
    override fun mapToDomainModel(model: RepositoriesQuery.Node): RepositoryNode {
        return RepositoryNode(
            name = model.name,
            description = model.description.orEmpty(),
            url = model.url.toString(),
            stargazersCount = model.stargazerCount
        )
    }

    override fun mapFromDomainModel(domainModel: RepositoryNode): RepositoriesQuery.Node {
        TODO("Not yet implemented")
    }

    fun nodes(nodes: List<RepositoriesQuery.Node>): List<RepositoryNode> {
        return nodes.map { mapToDomainModel(it) }
    }
}