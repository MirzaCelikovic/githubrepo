package com.toptal.githubrepo.domain.model.repository_details

data class RepositoryDetails(
    val openIssuesCount: Int = 0,
    val closedIssuesCount: Int = 0,
    val openPRsCount: Int = 0,
    val closedPRsCount: Int = 0,
    val openIssues: List<String> = listOf(),
    val openPRs: List<String> = listOf()
)