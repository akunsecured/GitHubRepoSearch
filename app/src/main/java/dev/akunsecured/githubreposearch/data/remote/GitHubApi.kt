package dev.akunsecured.githubreposearch.data.remote

import dev.akunsecured.githubreposearch.data.remote.dto.RepoSearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {

    @GET("/search/repositories")
    suspend fun searchRepositories(
        @Query("q") text: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 30,
    ): RepoSearchResult
}