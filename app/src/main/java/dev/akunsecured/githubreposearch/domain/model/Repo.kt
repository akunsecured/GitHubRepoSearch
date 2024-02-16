package dev.akunsecured.githubreposearch.domain.model

import java.io.Serializable
import java.util.Date

data class Repo(
    val id: Int,
    val name: String,
    val description: String?,
    val htmlUrl: String,
    val stargazersCount: Int,
    val forksCount: Int,
    val createdAt: Date,
    val updatedAt: Date,
    val owner: Owner,
) : Serializable
