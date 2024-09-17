package com.toptal.domain.model.repository_details

data class RepositoryDetails(
    val id: String,
    val viewerStarred: Boolean,
    val openIssuesCount: Int,
    val closedIssuesCount: Int,
    val openPRsCount: Int,
    val closedPRsCount: Int,
    val openIssues: List<String>,
    val openPRs: List<String>
) {
    companion object {
        fun empty(): RepositoryDetails {
            return RepositoryDetails(
                id = "",
                viewerStarred = false,
                openIssuesCount = 0,
                closedIssuesCount = 0,
                openPRsCount = 0,
                closedPRsCount = 0,
                openIssues = listOf(),
                openPRs = listOf()
            )
        }
    }
}