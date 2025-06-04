package com.fiap.aquavita.models

import com.squareup.moshi.Json

data class NewsResponse(val articles: List<Article>)

/**
 * Modelo de dados que representa um artigo de notícia.
 *
 * Esta classe é utilizada para deserializar artigos de notícias obtidos através
 * da API de notícias. Cada instância contém informações como título, descrição,
 * URL para o artigo completo, URL para uma imagem e data de publicação.
 */
data class Article(
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    @Json(name = "publishedAt") val publishedAt: String?
)