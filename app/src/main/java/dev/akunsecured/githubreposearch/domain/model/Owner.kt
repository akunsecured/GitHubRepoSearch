package dev.akunsecured.githubreposearch.domain.model

import java.io.Serializable

data class Owner(
    val name: String,
    val avatarUrl: String,
    val htmlUrl: String,
) : Serializable
