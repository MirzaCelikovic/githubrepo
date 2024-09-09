package com.toptal.githubrepo.data.network.model.repository_node

import com.github.RepositoriesQuery
import com.toptal.githubrepo.domain.DomainMapper
import com.toptal.githubrepo.domain.model.repository_nodes.RepositoryNode
import javax.inject.Inject

class RepositoryNodeMapper @Inject constructor(): DomainMapper<RepositoriesQuery.Node, RepositoryNode> {
    override fun mapToDomainModel(model: RepositoriesQuery.Node): RepositoryNode {
        return RepositoryNode(
            name = model.name,
            description = model.description.orEmpty(),
            url = model.url.toString()
        )
    }

    override fun mapFromDomainModel(domainModel: RepositoryNode): RepositoriesQuery.Node {
        TODO("Not yet implemented")
    }

    fun nodes(nodes: List<RepositoriesQuery.Node>): List<RepositoryNode> {
        return nodes.map { mapToDomainModel(it) }
    }
}