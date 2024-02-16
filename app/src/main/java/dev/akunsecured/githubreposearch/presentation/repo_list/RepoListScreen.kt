package dev.akunsecured.githubreposearch.presentation.repo_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.akunsecured.githubreposearch.presentation.repo_list.components.RepoListItem
import dev.akunsecured.githubreposearch.presentation.repo_list.components.RepoSearchTextField

@Composable
fun RepoListScreen(
    viewModel: RepoListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    var filledText by remember {
        mutableStateOf("")
    }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                RepoSearchTextField(
                    text = filledText,
                    onTextChanged = { filledText = it },
                    onButtonClicked = { viewModel.searchRepos(filledText) },
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
            items(state.repositories) {repo ->
                RepoListItem(
                    repo = repo,
                    onItemClick = {
                        // TODO: implement navigation
                    }
                )
            }
        }
        if (state.repositories.isEmpty() && state.error.isBlank() && !state.isLoading) {
            Text(
                text = "No results",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    }
}