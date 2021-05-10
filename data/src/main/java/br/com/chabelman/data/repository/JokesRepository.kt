package br.com.chabelman.data.repository

import br.com.chabelman.data.mapper.toJokeBO
import br.com.chabelman.data.remote.JokesApi
import br.com.chabelman.domain.model.JokeBo
import br.com.chabelman.domain.repository.IJokesRepository
import rx.Observable
import javax.inject.Inject

class JokesRepository @Inject constructor(
    private val jokesApi: JokesApi
): IJokesRepository {

    override fun getRandomJoke(category: String?): Observable<JokeBo> {
        return jokesApi.getRandomJoke(category).flatMap { jokeDto ->
            Observable.just(jokeDto.toJokeBO())
        }
    }
}