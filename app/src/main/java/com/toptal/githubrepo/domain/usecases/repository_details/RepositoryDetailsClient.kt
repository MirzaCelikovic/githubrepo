package com.toptal.githubrepo.domain.usecases.repository_details

import com.toptal.githubrepo.domain.model.repository_details.RepositoryDetails

interface RepositoryDetailsClient {
    suspend fun getRepositoryDetails(repositoryName: String): RepositoryDetails
}