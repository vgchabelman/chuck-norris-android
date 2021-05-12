package br.com.chabelman.domain.usecases

import br.com.chabelman.domain.model.JokeBo
import br.com.chabelman.domain.repository.IJokesRepository
import rx.Observable
import javax.inject.Inject

class JokeInteractor @Inject constructor(
    private val jokeRepository: IJokesRepository
) {
    fun getRandomJoke(category: String? = null): Observable<JokeBo> {
        return jokeRepository.getRandomJoke(category)
    }

    fun searchJokes(query: String): Observable<List<JokeBo>> {
        return jokeRepository.searchJokes(query)
    }

    fun changeJokeFavoriteStatus(joke: JokeBo, category: String? = null) {
        if (joke.isFavorite) {
            jokeRepository.unfavoriteJoke(joke)
        } else {
            jokeRepository.favoriteJoke(joke, category)
        }
    }

    fun isJokeFavorite(joke: JokeBo): Observable<Boolean> {
        return jokeRepository.getSavedJoke(joke.id)
            .flatMap {
                Observable.just(it != null)
            }
    }

    fun getFavoriteJokes(): Observable<List<JokeBo>> {
        return jokeRepository.getSavedJokes()
    }
}