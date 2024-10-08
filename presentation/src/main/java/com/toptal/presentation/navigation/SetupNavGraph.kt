package com.toptal.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.toptal.presentation.ui.repositories.RepositoriesScreen
import com.toptal.presentation.ui.repository_details.RepositoryDetailsScreen

/**
 * Viewmodel and state initializations should be done here instead passed in constructors,
 * and viewmodel methods should be passed via property accessor ::
 * This is to achieve easier testability of Composables and displaying previews
 */
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