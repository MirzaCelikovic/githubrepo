package com.toptal.domain.usecases.repository_nodes

import javax.inject.Inject

class AddStarUseCase @Inject constructor(private val repo: RepositoryNodeClient){
    suspend operator fun invoke(repoID: String): Boolean {
        return repo.addStar(repoID)
    }
}