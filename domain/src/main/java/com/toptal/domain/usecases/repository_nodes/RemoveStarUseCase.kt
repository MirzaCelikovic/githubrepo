package com.toptal.domain.usecases.repository_nodes

import javax.inject.Inject

class RemoveStarUseCase @Inject constructor(private val repo: RepositoryNodeClient){
    suspend operator fun invoke(repoID: String): Boolean {
        return repo.removeStar(repoID)
    }
}