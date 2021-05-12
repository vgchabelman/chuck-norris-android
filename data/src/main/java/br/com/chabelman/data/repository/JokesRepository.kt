package br.com.chabelman.data.repository

import android.util.Log
import br.com.chabelman.data.local.ChuckDatabase
import br.com.chabelman.data.local.JokeDao
import br.com.chabelman.data.local.JokeEntity
import br.com.chabelman.data.mapper.toJokeBO
import br.com.chabelman.data.mapper.toJokeBo
import br.com.chabelman.data.mapper.toJokeEntity
import br.com.chabelman.data.remote.JokesApi
import br.com.chabelman.domain.model.JokeBo
import br.com.chabelman.domain.repository.IJokesRepository
import rx.Observable
import javax.inject.Inject

class JokesRepository @Inject constructor(
    private val jokesApi: JokesApi,
    private val jokeDao: JokeDao
) : IJokesRepository {

    override fun getRandomJoke(category: String?): Observable<JokeBo> {
        return jokesApi.getRandomJoke(category).flatMap { jokeDto ->
            val jokeBo = jokeDto.toJokeBO()
            if (jokeDao.getJoke(jokeBo.id) != null) {
                jokeBo.isFavorite = true
            }

            Observable.just(jokeBo)
        }.doOnError {
            Log.e("chuckError", it.message, it)
        }
    }

    override fun searchJokes(query: String): Observable<List<JokeBo>> {
        return jokesApi.searchJokes(query).flatMap { searchResult ->
            Observable.just(
                searchResult.result.map {
                    val jokeBo = it.toJokeBO()
                    if (jokeDao.getJoke(jokeBo.id) != null) {
                        jokeBo.isFavorite = true
                    }
                    jokeBo
                }
            )
        }
    }

    override fun favoriteJoke(jokeBo: JokeBo, category: String?) {
        jokeDao.insert(jokeBo.toJokeEntity(category))
    }

    override fun unfavoriteJoke(jokeBo: JokeBo) {
        jokeDao.delete(jokeBo.toJokeEntity())
    }

    override fun getSavedJokes(): Observable<List<JokeBo>> {
        return Observable.just(
            jokeDao.getAllJoke().map {
                it.toJokeBo()
            }
        )
    }

    override fun getSavedJoke(id: String): Observable<JokeBo?> {
        return Observable.just(
            jokeDao.getJoke(id)?.toJokeBo()
        )
    }
}