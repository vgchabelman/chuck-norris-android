package br.com.chabelman.data.repository

import rx.Observable
import br.com.chabelman.data.remote.JokeDto
import br.com.chabelman.data.remote.JokesApi
import javax.inject.Inject

class JokesRepository @Inject constructor(
    private val jokesApi: JokesApi
) {

    fun getRandomJoke(category: String? = null): Observable<JokeDto> {
        return jokesApi.getRandomJoke(category)
    }
}