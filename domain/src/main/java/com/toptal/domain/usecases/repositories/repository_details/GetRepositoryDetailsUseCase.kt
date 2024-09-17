package com.toptal.domain.usecases.repositories.repository_details

import com.toptal.domain.model.repository_details.RepositoryDetails
import com.toptal.domain.usecases.repositories.RepositoryClient
import javax.inject.Inject

class GetRepositoryDetailsUseCase
@Inject constructor(private val client: RepositoryClient) {
    suspend operator fun invoke(repositoryTitle: String): RepositoryDetails {
        return client.getRepositoryDetails(repositoryTitle)
    }
}