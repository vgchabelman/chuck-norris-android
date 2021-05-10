package br.com.chabelman.domain.repository

import br.com.chabelman.domain.model.JokeBo
import rx.Observable

interface IJokesRepository {

    fun getRandomJoke(category: String?): Observable<JokeBo>
}