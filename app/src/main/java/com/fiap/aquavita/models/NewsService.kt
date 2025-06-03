package com.fiap.aquavita.models

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_QUERY =
    "(\"secas prolongadas\" OR seca OR estiagem OR \"crise hídrica\" OR \"racionamento de água\" " +
            "OR \"uso consciente da água\" OR \"economia de água\")"

interface NewsService {
    @GET("v2/everything")
    suspend fun getWaterNews(
        @Query("q") query: String = BASE_QUERY,
        @Query("language") lang: String = "pt",
        @Query("apiKey") apiKey: String = NEWS_API_KEY,
        @Query("pageSize") size: Int = 14
    ): NewsResponse
}

private const val NEWS_API_KEY = "5d74ee1f2b0e44068c2989c677e819ea"
fun provideNewsService(): NewsService = Retrofit.Builder()
    .baseUrl("https://newsapi.org/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()
    .create(NewsService::class.java)