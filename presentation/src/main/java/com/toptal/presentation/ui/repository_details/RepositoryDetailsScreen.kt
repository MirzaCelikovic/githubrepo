package com.toptal.presentation.ui.repository_details

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.toptal.domain.model.repository_details.RepositoryDetails

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RepositoryDetailsScreen(
    modifier: Modifier = Modifier,
    repositoryName: String,
    state: RepositoryDetailsViewModel.RepositoryDetailsState,
    fetchDetails: (repositoryName: String) -> Unit
) {

    LaunchedEffect(Unit) {
        fetchDetails(repositoryName)
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
                item {
                    HeadingSection(
                        repoDetails = state.repoDetails
                    )
                }

                val openIssuesCount = state.repoDetails.openIssuesCount
                if (openIssuesCount > 0) {
                    stickyHeader {
                        Text(
                            text = "Open issues",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = modifier
                                .background(color = Color.White)
                                .fillMaxWidth()
                                .padding(8.dp)
                        )
                    }
                    items(state.repoDetails.openIssues) {
                        ListItem(itemText = "• $it")
                        HorizontalDivider()
                    }
                }

                val openPrsCount = state.repoDetails.openPRsCount
                if (openPrsCount > 0) {
                    stickyHeader {
                        Text(
                            text = "Open PRs",
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = modifier
                                .background(color = Color.White)
                                .fillMaxWidth()
                                .padding(8.dp)
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
}

@Composable
fun HeadingSection(
    modifier: Modifier = Modifier,
    repoDetails: RepositoryDetails
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Open issues count: ${repoDetails.openIssuesCount}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Closed issues count: ${repoDetails.closedIssuesCount}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Open PRs count: ${repoDetails.openPRsCount}",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Closed PRs count: ${repoDetails.closedPRsCount}",
            style = MaterialTheme.typography.bodyLarge
        )
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

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
fun RepositoryDetailsScreenPreview() {
    RepositoryDetailsScreen(
        modifier = Modifier.background(color = Color.White),
        repositoryName = "repo",
        state = RepositoryDetailsViewModel.RepositoryDetailsState(repoDetails = RepositoryDetails.empty(), isLoading = false),
        fetchDetails = { "s" }
    )
}