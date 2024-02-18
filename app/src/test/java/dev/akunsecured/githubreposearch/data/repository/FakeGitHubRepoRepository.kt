package dev.akunsecured.githubreposearch.data.repository

import androidx.paging.PagingData
import dev.akunsecured.githubreposearch.domain.model.Repo
import dev.akunsecured.githubreposearch.domain.repository.GitHubRepoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Locale

class FakeGitHubRepoRepository : GitHubRepoRepository {

    private val repos = mutableListOf<Repo>()

    fun insertRepos(repos: List<Repo>) {
        this.repos.addAll(repos)
    }

    override suspend fun getRepos(text: String): Flow<PagingData<Repo>> {
        return flow {
            emit(
                PagingData.from(
                    repos.filter {
                        it.name.lowercase(Locale.getDefault())
                            .contains(text.lowercase(Locale.getDefault()))
                    }
                )
            )
        }
    }
}