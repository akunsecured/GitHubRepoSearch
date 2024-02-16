package dev.akunsecured.githubreposearch.common

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

object Constants {

    const val BASE_URL = "https://api.github.com/"

    const val MAX_PAGE_SIZE = 100

    @SuppressLint("SimpleDateFormat")
    val FORMATTER = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

    @SuppressLint("SimpleDateFormat")
    val DISPLAY_DATA_FORMATTER = SimpleDateFormat("yyyy.MM.dd HH:mm")
}