package com.toptal.data.network.model.repository_details

import com.apollographql.apollo3.ApolloClient
import com.github.RepositoryDetailsQuery
import com.toptal.domain.ORGANIZATION_NAME
import com.toptal.domain.model.repository_details.RepositoryDetails
import com.toptal.domain.usecases.repository_details.RepositoryDetailsClient
import javax.inject.Inject

/**
 * No error handling in place, would use Result class and combine it with state for loading, success and error states
 */
class RepositoryDetailsImpl
@Inject constructor(
    private val apolloClient: ApolloClient,
    private val mapper: RepositoryDetailsMapper
): RepositoryDetailsClient {
    override suspend fun getRepositoryDetails(repositoryName: String): RepositoryDetails {
        return apolloClient
            .query(RepositoryDetailsQuery(orgName = ORGANIZATION_NAME, repoName = repositoryName))
            .execute()
            .data
            ?.repository
            ?.let { mapper.mapToDomainModel(it) }
            ?: throw IllegalArgumentException("No details found for $repositoryName")
    }
}