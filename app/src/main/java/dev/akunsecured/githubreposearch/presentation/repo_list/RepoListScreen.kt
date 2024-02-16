package dev.akunsecured.githubreposearch.presentation.repo_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import dev.akunsecured.githubreposearch.domain.model.Repo
import dev.akunsecured.githubreposearch.presentation.repo_list.components.RepoListItem
import dev.akunsecured.githubreposearch.presentation.repo_list.components.RepoSearchTextField
import dev.akunsecured.githubreposearch.presentation.util.ErrorMessage
import dev.akunsecured.githubreposearch.presentation.util.LoadingNextPageItem
import dev.akunsecured.githubreposearch.presentation.util.PageLoader

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RepoListScreen(
    viewModel: RepoListViewModel = hiltViewModel()
) {
    val repoPagingItems: LazyPagingItems<Repo> = viewModel.state.collectAsLazyPagingItems()
    val searchState = viewModel.searchState

    var filledText by remember {
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                RepoSearchTextField(
                    text = filledText,
                    onTextChanged = { filledText = it },
                    onButtonClicked = {
                        keyboardController?.hide()

                        viewModel.onEvent(
                            RepoListEvent.SearchRepo(text = filledText)
                        )
                    },
                    modifier = Modifier
                        .padding(16.dp)
                )
            }
            items(repoPagingItems.itemCount) { index ->
                RepoListItem(
                    repo = repoPagingItems[index]!!,
                    onItemClick = {
                        // TODO: implement navigation
                    }
                )
            }
            repoPagingItems.apply {
                when {
                    repoPagingItems.itemCount == 0 && loadState.refresh is LoadState.NotLoading && searchState.value !is RepoListState.Initial -> {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillParentMaxSize()
                            ) {
                                Text(
                                    text = "No results",
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                )
                            }
                        }
                    }

                    loadState.refresh is LoadState.Loading && searchState.value !is RepoListState.Initial -> {
                        item {
                            PageLoader(
                                modifier = Modifier
                                    .fillParentMaxSize()
                            )
                        }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val error = repoPagingItems.loadState.refresh as LoadState.Error

                        item {
                            ErrorMessage(
                                message = error.error.localizedMessage!!,
                                onClickRetry = { retry() },
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item {
                            LoadingNextPageItem(
                                modifier = Modifier
                            )
                        }
                    }

                    loadState.append is LoadState.Error -> {
                        val error = repoPagingItems.loadState.append as LoadState.Error

                        item {
                            ErrorMessage(
                                message = error.error.localizedMessage!!,
                                onClickRetry = {
                                    retry()
                                },
                                modifier = Modifier
                            )
                        }
                    }
                }
            }
        }
    }
}