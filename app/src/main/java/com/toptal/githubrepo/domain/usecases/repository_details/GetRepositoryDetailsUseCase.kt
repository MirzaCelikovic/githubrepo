package com.toptal.githubrepo.domain.usecases.repository_details

import com.toptal.githubrepo.domain.model.repository_details.RepositoryDetails
import com.toptal.githubrepo.domain.model.repository_nodes.RepositoryNode
import javax.inject.Inject

class GetRepositoryDetailsUseCase
@Inject constructor(private val client: RepositoryDetailsClient) {
    suspend operator fun invoke(repositoryTitle: String): RepositoryDetails {
        return client.getRepositoryDetails(repositoryTitle)
    }
}