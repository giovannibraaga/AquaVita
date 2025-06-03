package com.fiap.aquavita.models

import com.squareup.moshi.Json

data class NewsResponse(val articles: List<Article>)

data class Article(
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    @Json(name = "publishedAt") val publishedAt: String?
)