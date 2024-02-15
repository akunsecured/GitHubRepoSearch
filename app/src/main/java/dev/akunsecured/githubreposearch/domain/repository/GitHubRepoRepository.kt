package dev.akunsecured.githubreposearch.domain.repository

import dev.akunsecured.githubreposearch.data.remote.dto.RepoDto

interface GitHubRepoRepository {

    suspend fun getRepos(text: String, page: Int): List<RepoDto>
}