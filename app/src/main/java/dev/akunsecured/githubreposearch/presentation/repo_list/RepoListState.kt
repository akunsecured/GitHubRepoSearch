package dev.akunsecured.githubreposearch.presentation.repo_list

import dev.akunsecured.githubreposearch.domain.model.Repo

data class RepoListState(
    val isLoading: Boolean = false,
    val repositories: List<Repo> = emptyList(),
    val error: String = "",
)