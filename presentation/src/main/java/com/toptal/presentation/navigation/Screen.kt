package com.toptal.presentation.navigation

sealed class Screen(val route: String) {
    data object Repositories: Screen("repositories")
    data object RepositoryDetails: Screen("repository_details/{repositoryName}")
}