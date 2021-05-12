package br.com.chabelman.domain.usecases

import br.com.chabelman.domain.model.JokeBo
import br.com.chabelman.domain.repository.IJokesRepository
import rx.Observable
import rx.schedulers.Schedulers
import javax.inject.Inject

class JokeInteractor @Inject constructor(
    private val jokeRepository: IJokesRepository
){
    fun getRandomJoke(category: String? = null): Observable<JokeBo> {
        return jokeRepository.getRandomJoke(category)
    }

    fun searchJokes(query: String): Observable<List<JokeBo>> {
        return jokeRepository.searchJokes(query)
    }
}