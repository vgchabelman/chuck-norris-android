package br.com.chabelman.data.remote

import br.com.chabelman.data.RANDOM
import br.com.chabelman.data.SEARCH
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface JokesApi {

    @GET(RANDOM)
    fun getRandomJoke(
        @Query("category") category: String?
    ): Observable<JokeDto>

    @GET(SEARCH)
    fun searchJokes(
        @Query("query") query: String
    ): Observable<JokeResponseDto>
}