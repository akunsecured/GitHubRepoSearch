package dev.akunsecured.githubreposearch.data.remote.dto


import com.google.gson.annotations.SerializedName

data class RepoSearchResult(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    val items: List<RepoDto>,
    @SerializedName("total_count")
    val totalCount: Int
)