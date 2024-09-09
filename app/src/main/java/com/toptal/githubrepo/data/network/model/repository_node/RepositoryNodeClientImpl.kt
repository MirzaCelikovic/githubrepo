package com.toptal.githubrepo.data.network.model.repository_node

import com.apollographql.apollo3.ApolloClient
import com.github.RepositoriesQuery
import com.toptal.githubrepo.domain.ORGANIZATION_NAME
import com.toptal.githubrepo.domain.model.repository_nodes.RepositoryNode
import com.toptal.githubrepo.domain.usecases.repository_nodes.RepositoryNodeClient
import javax.inject.Inject

class RepositoryNodeClientImpl
@Inject constructor(
    private val apolloClient: ApolloClient,
    private val mapper: RepositoryNodeMapper
): RepositoryNodeClient {
    override suspend fun getRepositories(): List<RepositoryNode> {
           return apolloClient
               .query(RepositoriesQuery(ORGANIZATION_NAME))
               .execute()
               .data
               ?.organization
               ?.repositories
               ?.edges
               ?.mapNotNull { it?.node }
               ?.map { mapper.mapToDomainModel(it) }
               ?: emptyList()
    }
}