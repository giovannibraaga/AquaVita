package com.fiap.aquavita.services

import com.fiap.aquavita.models.NewsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Consulta base que contém termos relacionados à crise hídrica e conservação de água.
 * Esta string é utilizada como parâmetro de busca padrão para a API de notícias.
 */
private const val BASE_QUERY =
    "(seca OR estiagem OR \"crise hídrica\" OR \"secas prolongadas\" OR \"racionamento de água\" " +
            "OR \"uso consciente da água\" OR \"economia de água\")"

/**
 * Interface que define os endpoints para buscar notícias relacionadas à água.
 *
 * Esta interface utiliza a biblioteca Retrofit para comunicação com a News API,
 * permitindo que o aplicativo busque notícias atualizadas sobre temas relacionados
 * à água, conservação hídrica e crises de abastecimento.
 */
interface NewsService {
    /**
     * Busca notícias relacionadas à água e crises hídricas.
     *
     * @param query Termos de busca para filtrar as notícias. Por padrão, utiliza [BASE_QUERY]
     * @param lang Idioma das notícias a serem retornadas. Por padrão, "pt" (português)
     * @param apiKey Chave de API para autenticação na News API. Por padrão, usa [NEWS_API_KEY]
     * @param size Número máximo de notícias a serem retornadas. Por padrão, 14 artigos
     * @return Um objeto [NewsResponse] contendo a lista de artigos de notícias e metadados da resposta
     */
    @GET("v2/everything")
    suspend fun getWaterNews(
        @Query("q") query: String = BASE_QUERY,
        @Query("language") lang: String = "pt",
        @Query("apiKey") apiKey: String = NEWS_API_KEY,
        @Query("pageSize") size: Int = 14
    ): NewsResponse
}

private const val NEWS_API_KEY = "5d74ee1f2b0e44068c2989c677e819ea"

/**
 * Fornece uma implementação da interface [NewsService] configurada com Retrofit.
 *
 * Esta função cria e configura um cliente Retrofit com a URL base da News API
 * e um conversor Gson para transformar as respostas JSON em objetos Kotlin.
 *
 * @return Uma implementação da interface [NewsService] pronta para uso
 *
*/
fun provideNewsService(): NewsService =
    Retrofit.Builder()
        .baseUrl("https://newsapi.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsService::class.java)