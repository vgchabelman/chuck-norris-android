package br.com.chabelman.domain.repository

import br.com.chabelman.domain.model.JokeBo
import rx.Observable

interface IJokesRepository {

    fun getRandomJoke(category: String?): Observable<JokeBo>

    fun searchJokes(query: String): Observable<List<JokeBo>>

    fun favoriteJoke(jokeBo: JokeBo, category: String?)

    fun unfavoriteJoke(jokeBo: JokeBo)

    fun getSavedJokes(): Observable<List<JokeBo>>

    fun getSavedJoke(id: String): Observable<JokeBo?>
}