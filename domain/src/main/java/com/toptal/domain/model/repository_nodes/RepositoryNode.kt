package com.toptal.domain.model.repository_nodes

data class RepositoryNode(
    val name: String,
    val description: String,
    val url: String,
    val viewerStarred: Boolean
) {
    fun isEvenNoOfLetters(): Boolean {
        return url.count() % 2 == 0
    }
}