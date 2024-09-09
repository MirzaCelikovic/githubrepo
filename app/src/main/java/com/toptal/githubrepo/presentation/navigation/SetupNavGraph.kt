package com.toptal.githubrepo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.toptal.githubrepo.presentation.ui.repositories.RepositoriesScreen
import com.toptal.githubrepo.presentation.ui.repository_details.RepositoryDetailsScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Repositories.route
    ) {
        composable(route = Screen.Repositories.route) {
            RepositoriesScreen { repoName ->
                val repoDetailsRoute = Screen.RepositoryDetails.route.replace(
                    oldValue = "{repositoryName}",
                    newValue = repoName
                )
                navController.navigate(repoDetailsRoute)
            }
        }

        composable(route = Screen.RepositoryDetails.route) {
            val repoName = it.arguments?.getString("repositoryName")!!
            RepositoryDetailsScreen(repositoryName = repoName)
        }
    }
}