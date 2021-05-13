package br.com.chabelman.domain

import br.com.chabelman.domain.model.JokeBo
import br.com.chabelman.domain.model.TEST_JOKE
import br.com.chabelman.domain.repository.JokesRepositoryMock
import br.com.chabelman.domain.usecases.JokeInteractor
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class JokeInteractorTest {
    lateinit var interactor: JokeInteractor

    @Before
    fun setup() {
        interactor = JokeInteractor(JokesRepositoryMock())
    }

    @Test
    fun `it should return a joke from repository`() {
        var joke: JokeBo? = null

        interactor.getRandomJoke()
            .subscribe {
                joke = it
            }

        Assert.assertEquals(TEST_JOKE, joke)
    }

    @Test
    fun `it should favorite Joke when it not yet favorite`() {
        val joke = TEST_JOKE
        joke.isFavorite = false

        interactor.changeJokeFavoriteStatus(joke)

        Assert.assertEquals(true, joke.isFavorite)
    }

    @Test
    fun `it should unfavorite Joke when it is favorite already`() {
        val joke = TEST_JOKE
        joke.isFavorite = true

        interactor.changeJokeFavoriteStatus(joke)

        Assert.assertEquals(false, joke.isFavorite)
    }

    @Test
    fun `it should return repository joke list`() {
        val jokeList: MutableList<JokeBo> = mutableListOf()

        interactor.searchJokes("")
            .subscribe {
                jokeList.addAll(it)
            }

        Assert.assertEquals(JokesRepositoryMock.TEST_TOKE_LIST, jokeList)
    }

    @Test
    fun `it should return repository favorite jokes list`() {
        val jokeList: MutableList<JokeBo> = mutableListOf()

        interactor.getFavoriteJokes()
            .subscribe {
                jokeList.addAll(it)
            }

        Assert.assertEquals(JokesRepositoryMock.TEST_TOKE_LIST, jokeList)
    }
}