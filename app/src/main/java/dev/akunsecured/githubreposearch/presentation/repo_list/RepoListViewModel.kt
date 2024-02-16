package dev.akunsecured.githubreposearch.presentation.repo_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.akunsecured.githubreposearch.common.Resource
import dev.akunsecured.githubreposearch.domain.use_case.search_repos.SearchReposUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    private val searchReposUseCase: SearchReposUseCase
) : ViewModel() {

    private val _state = mutableStateOf(RepoListState())
    val state: State<RepoListState> = _state

    fun searchRepos(text: String) {
        searchReposUseCase(text = text, page = 1).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = RepoListState(repositories = result.data ?: emptyList())
                }

                is Resource.Loading -> {
                    _state.value = RepoListState(isLoading = true)
                }

                is Resource.Error -> {
                    _state.value =
                        RepoListState(error = result.message ?: "An unexpected error occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}