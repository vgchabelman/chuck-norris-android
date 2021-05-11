package br.com.chabelman.data.remote

import br.com.chabelman.data.RANDOM
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface JokesApi {

    @GET(RANDOM)
    fun getRandomJoke(
        @Query("category") category: String?
    ): Observable<JokeDto>
}