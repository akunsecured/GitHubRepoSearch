package dev.akunsecured.githubreposearch.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.akunsecured.githubreposearch.common.Constants
import dev.akunsecured.githubreposearch.data.remote.GitHubApi
import dev.akunsecured.githubreposearch.data.repository.paging.RepoPagingSource
import dev.akunsecured.githubreposearch.domain.model.Repo
import dev.akunsecured.githubreposearch.domain.repository.GitHubRepoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GitHubRepoRepositoryImpl @Inject constructor(
    private val api: GitHubApi
) : GitHubRepoRepository {

    override suspend fun getRepos(text: String): Flow<PagingData<Repo>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.MAX_PAGE_SIZE,
                prefetchDistance = 2,
            ),
            pagingSourceFactory = {
                RepoPagingSource(api, text)
            }
        ).flow
    }
}