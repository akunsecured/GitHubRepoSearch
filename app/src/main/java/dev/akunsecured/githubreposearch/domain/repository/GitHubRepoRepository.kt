package dev.akunsecured.githubreposearch.domain.repository

import androidx.paging.PagingData
import dev.akunsecured.githubreposearch.domain.model.Repo
import kotlinx.coroutines.flow.Flow

interface GitHubRepoRepository {

    suspend fun getRepos(text: String): Flow<PagingData<Repo>>
}