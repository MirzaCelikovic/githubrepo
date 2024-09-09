package com.toptal.githubrepo.data.network.model.repository_details

import com.apollographql.apollo3.ApolloClient
import com.github.RepositoryDetailsQuery
import com.toptal.githubrepo.domain.ORGANIZATION_NAME
import com.toptal.githubrepo.domain.model.repository_details.RepositoryDetails
import com.toptal.githubrepo.domain.usecases.repository_details.RepositoryDetailsClient
import javax.inject.Inject

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