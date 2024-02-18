package dev.akunsecured.githubreposearch.presentation.repo_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.akunsecured.githubreposearch.domain.model.Repo
import dev.akunsecured.githubreposearch.domain.use_case.search_repos.SearchReposUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoListViewModel @Inject constructor(
    private val searchReposUseCase: SearchReposUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<PagingData<Repo>> =
        MutableStateFlow(value = PagingData.empty())
    val state: MutableStateFlow<PagingData<Repo>> get() = _state

    private val _searchState = mutableStateOf<RepoListState>(RepoListState.Initial)
    val searchState: MutableState<RepoListState> get() = _searchState

    fun onEvent(event: RepoListEvent) {
        _searchState.value = RepoListState.Searched

        viewModelScope.launch {
            when (event) {
                is RepoListEvent.SearchRepo -> {
                    searchRepos(text = event.text)
                }
            }
        }
    }

    private suspend fun searchRepos(text: String) {
        searchReposUseCase(text = text)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _state.value = it
            }
    }
}

sealed class RepoListEvent {

    data class SearchRepo(val text: String) : RepoListEvent()
}

sealed class RepoListState {

    data object Initial : RepoListState()
    data object Searched : RepoListState()
}