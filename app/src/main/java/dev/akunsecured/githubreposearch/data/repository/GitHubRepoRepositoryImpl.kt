package dev.akunsecured.githubreposearch.data.repository

import dev.akunsecured.githubreposearch.data.remote.GitHubApi
import dev.akunsecured.githubreposearch.data.remote.dto.RepoDto
import dev.akunsecured.githubreposearch.domain.repository.GitHubRepoRepository

class GitHubRepoRepositoryImpl constructor(
    private val api: GitHubApi
) : GitHubRepoRepository {

    override suspend fun getRepos(text: String, page: Int): List<RepoDto> {
        return api.searchRepositories(text = text, page = page).items
    }
}