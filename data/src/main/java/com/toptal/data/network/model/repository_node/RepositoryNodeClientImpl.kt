package com.toptal.data.network.model.repository_node

import com.apollographql.apollo3.ApolloClient
import com.github.RepositoriesQuery
import com.toptal.domain.ORGANIZATION_NAME
import com.toptal.domain.model.repository_nodes.RepositoryNode
import com.toptal.domain.usecases.repository_nodes.RepositoryNodeClient
import javax.inject.Inject

/**
 * No error handling in place, would use Result class and combine it with state for loading, success and error states
 */
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