package br.com.chabelman.domain.repository

import br.com.chabelman.domain.model.JokeBo
import br.com.chabelman.domain.model.TEST_JOKE
import rx.Observable

class JokesRepositoryMock : IJokesRepository {
    companion object {
        val TEST_TOKE_LIST = listOf(TEST_JOKE)
    }

    override fun getRandomJoke(category: String?): Observable<JokeBo> {
        return Observable.just(TEST_JOKE)
    }

    override fun searchJokes(query: String): Observable<List<JokeBo>> {
        return Observable.just(TEST_TOKE_LIST)
    }

    override fun favoriteJoke(jokeBo: JokeBo, category: String?) {
        jokeBo.isFavorite = true
    }

    override fun unfavoriteJoke(jokeBo: JokeBo) {
        jokeBo.isFavorite = false
    }

    override fun getSavedJokes(): Observable<List<JokeBo>> {
        return Observable.just(TEST_TOKE_LIST)
    }

    override fun getSavedJoke(id: String): Observable<JokeBo?> {
        if (id == TEST_JOKE.id) {
            return Observable.just(TEST_JOKE)
        }
        return Observable.just(null)
    }
}