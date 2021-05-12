package br.com.chabelman.data.repository

import android.util.Log
import br.com.chabelman.data.mapper.toJokeBO
import br.com.chabelman.data.remote.JokesApi
import br.com.chabelman.domain.model.JokeBo
import br.com.chabelman.domain.repository.IJokesRepository
import rx.Observable
import javax.inject.Inject

class JokesRepository @Inject constructor(
    private val jokesApi: JokesApi
) : IJokesRepository {

    override fun getRandomJoke(category: String?): Observable<JokeBo> {
        return jokesApi.getRandomJoke(category).flatMap { jokeDto ->
            Observable.just(jokeDto.toJokeBO())
        }.doOnError {
            Log.e("chuckError", it.message, it)
        }
    }

    override fun searchJokes(query: String): Observable<List<JokeBo>> {
        return jokesApi.searchJokes(query).flatMap { searchResult ->
            Observable.just(
                searchResult.result.map { it.toJokeBO() }
            )
        }
    }
}