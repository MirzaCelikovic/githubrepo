package com.toptal.data.network.model.repository_node

import com.apollographql.apollo3.ApolloClient
import com.github.RepositoriesQuery
import com.github.RepositoryDetailsQuery
import com.toptal.data.network.model.repository_details.RepositoryDetailsMapper
import com.toptal.domain.ORGANIZATION_NAME
import com.toptal.domain.model.repository_details.RepositoryDetails
import com.toptal.domain.model.repository_nodes.RepositoryNode
import com.toptal.domain.usecases.repositories.RepositoryClient
import javax.inject.Inject

/**
 * No error handling in place, would use Result class and combine it with state for loading, success and error states
 */
class RepositoryClientImpl
@Inject constructor(
    private val apolloClient: ApolloClient,
    private val repositoryNodeMapper: RepositoryNodeMapper,
    private val repositoryDetailsMapper: RepositoryDetailsMapper
): RepositoryClient {
    override suspend fun getRepositories(): List<RepositoryNode> {
           return apolloClient
               .query(RepositoriesQuery(ORGANIZATION_NAME))
               .execute()
               .data
               ?.organization
               ?.repositories
               ?.edges
               ?.mapNotNull { it?.node }
               ?.map { repositoryNodeMapper.mapToDomainModel(it) }
               ?: emptyList()
    }

    override suspend fun getRepositoryDetails(repositoryName: String): RepositoryDetails {
        return apolloClient
            .query(RepositoryDetailsQuery(orgName = ORGANIZATION_NAME, repoName = repositoryName))
            .execute()
            .data
            ?.repository
            ?.let { repositoryDetailsMapper.mapToDomainModel(it) }
            ?: throw IllegalArgumentException("No details found for $repositoryName")
    }
}