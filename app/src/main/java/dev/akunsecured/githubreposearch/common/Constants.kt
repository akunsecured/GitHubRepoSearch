package dev.akunsecured.githubreposearch.common

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object Constants {

    const val BASE_URL = "https://api.github.com/"

    @SuppressLint("SimpleDateFormat")
    val FORMATTER = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
}