package com.toptal.presentation.ui.repository_details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Star
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RepositoryDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: RepositoryDetailsViewModel = hiltViewModel(),
    repositoryName: String
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchRepoDetails(repositoryName)
    }

    Box(modifier = modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(
                modifier = modifier.padding(vertical = 48.dp, horizontal = 8.dp)
            ) {
                stickyHeader {
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Open issues count: ${state.repoDetails.openIssuesCount}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Closed issues count: ${state.repoDetails.closedIssuesCount}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Open PRs count: ${state.repoDetails.openPRsCount}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Text(
                            text = "Closed PRs count: ${state.repoDetails.closedPRsCount}",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Box(modifier = Modifier
                            .clickable {
                                viewModel.toggleStar()
                            }
                        ) {
                            if (state.stared) {
                                Icon(Icons.Filled.Star, "star")
                            } else {
                                Icon(Icons.Outlined.MailOutline, "star")
                            }
                        }
                    }
                }

                stickyHeader {
                    Spacer(modifier = modifier.padding(16.dp))
                    Text(
                        text = "Open issues",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                items(state.repoDetails.openIssues) {
                    ListItem(itemText = "• $it")
                    HorizontalDivider()
                }

                stickyHeader {
                    Spacer(modifier = modifier.padding(16.dp))
                    Text(
                        text = "Open PRs",
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                items(state.repoDetails.openPRs) {
                    ListItem(itemText = "• $it")
                    HorizontalDivider()
                }
            }
        }
    }
}

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    itemText: String
) {
    Text(
        modifier = modifier.padding(vertical = 4.dp),
        text = itemText,
        style = MaterialTheme.typography.bodySmall
    )
}