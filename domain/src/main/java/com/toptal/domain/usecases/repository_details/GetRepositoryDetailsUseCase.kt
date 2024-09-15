package com.toptal.domain.usecases.repository_details

import com.toptal.domain.model.repository_details.RepositoryDetails
import javax.inject.Inject

class GetRepositoryDetailsUseCase
@Inject constructor(private val client: RepositoryDetailsClient) {
    suspend operator fun invoke(repositoryTitle: String): RepositoryDetails {
        return client.getRepositoryDetails(repositoryTitle)
    }
}