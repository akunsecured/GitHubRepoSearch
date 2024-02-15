package dev.akunsecured.githubreposearch.data.remote.dto


import com.google.gson.annotations.SerializedName

data class License(
    @SerializedName("html_url")
    val htmlUrl: String,
    val key: String,
    val name: String,
    @SerializedName("node_id")
    val nodeId: String,
    @SerializedName("spdx_id")
    val spdxId: String,
    val url: String
)