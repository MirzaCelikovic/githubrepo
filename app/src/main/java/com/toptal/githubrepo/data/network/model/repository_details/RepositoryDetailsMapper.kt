package com.toptal.githubrepo.data.network.model.repository_details

import com.github.RepositoryDetailsQuery
import com.toptal.githubrepo.domain.DomainMapper
import com.toptal.githubrepo.domain.model.repository_details.RepositoryDetails
import javax.inject.Inject

/**
 * This could be split into several mappers so each mapper handles each generated data class
 */
class RepositoryDetailsMapper @Inject constructor(): DomainMapper<RepositoryDetailsQuery.Repository, RepositoryDetails> {
    override fun mapToDomainModel(model: RepositoryDetailsQuery.Repository): RepositoryDetails {
        return RepositoryDetails(
            openIssuesCount = model.openIssues.totalCount,
            closedIssuesCount = model.closedIssues.totalCount,
            openPRsCount = model.openPullRequests.totalCount,
            closedPRsCount = model.closedPullRequests.totalCount,
            openIssues = model.openIssues.nodes?.map { it?.title.orEmpty() } ?: emptyList(),
            openPRs = model.openPullRequests.nodes?.map { it?.title.orEmpty() } ?: emptyList()
        )
    }

    override fun mapFromDomainModel(domainModel: RepositoryDetails): RepositoryDetailsQuery.Repository {
        TODO("Not yet implemented")
    }

}