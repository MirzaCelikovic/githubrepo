package com.toptal.githubrepo.domain.usecases.repository_details

import com.toptal.githubrepo.domain.model.repository_details.RepositoryDetails

/**
 * Should be called a repository per repository pattern but named client to avoid
 */
interface RepositoryDetailsClient {
    suspend fun getRepositoryDetails(repositoryName: String): RepositoryDetails
}