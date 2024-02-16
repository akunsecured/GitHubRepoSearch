package dev.akunsecured.githubreposearch.domain.use_case.search_repos

import androidx.paging.PagingData
import dev.akunsecured.githubreposearch.domain.model.Repo
import dev.akunsecured.githubreposearch.domain.repository.GitHubRepoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchReposUseCase @Inject constructor(
    private val repository: GitHubRepoRepository
) {

    suspend operator fun invoke(text: String): Flow<PagingData<Repo>> {
        return repository.getRepos(text)
    }
}