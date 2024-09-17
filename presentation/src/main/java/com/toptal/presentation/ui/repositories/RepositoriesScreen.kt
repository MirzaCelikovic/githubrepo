package com.toptal.presentation.ui.repositories

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.toptal.domain.model.repository_nodes.RepositoryNode
import com.toptal.presentation.R

@Composable
fun RepositoriesScreen(
    modifier: Modifier = Modifier,
    viewModel: RepositoriesViewModel = hiltViewModel(),
    onNavigateToRepoDetailsScreen: (repoName: String) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.fetchRepos()
    }

    val state by viewModel.state.collectAsState()

    Box(modifier = modifier
        .fillMaxSize()
        .padding(vertical = 48.dp, horizontal = 8.dp)) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.repos) { repo ->
                    RepoItem(repo = repo, onRepoClick = onNavigateToRepoDetailsScreen)
                    HorizontalDivider(modifier = modifier.height(1.dp))
                }
            }
        }
    }
}

@Composable
fun RepoItem(
    modifier: Modifier = Modifier,
    repo: RepositoryNode,
    onRepoClick: (repoName: String) -> Unit
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .clickable {
                onRepoClick(repo.name)
            },
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = repo.name, style = MaterialTheme.typography.headlineSmall, maxLines = 1)
        Text(text = repo.url, style = MaterialTheme.typography.bodySmall)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.Star, contentDescription = null)
            Text(text = repo.stargazersCount.toString(), style = MaterialTheme.typography.bodyMedium)
        }
    }
}