package br.com.chabelman.presentation.jokedetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.chabelman.domain.usecases.JokeInteractor
import br.com.chabelman.presentation.TEST_JOKE
import br.com.chabelman.presentation.favorites.FavoritesViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import rx.Observable

@ExperimentalCoroutinesApi
class JokeDetailViewModelTest {
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var viewModel: JokeDetailViewModel

    @MockK
    var interactor = mockk<JokeInteractor>()

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        viewModel = JokeDetailViewModel()
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel.jokeInteractor = interactor
    }

    @Test
    fun `it should get random joke from interactor`() {
        val testCategory = "test category"
        every {
            interactor.getRandomJoke(testCategory)
        } returns Observable.just(TEST_JOKE)

        runBlockingTest {
            viewModel.getRandomJoke(testCategory)
            Thread.sleep(50)
            Assert.assertEquals(TEST_JOKE, viewModel.joke.value)
        }
    }

    @Test
    fun `it should call interactor to change favorite status`() {
        runBlockingTest {
            viewModel.saveFavoriteStatus(TEST_JOKE)
            verify(exactly = 1) { interactor.changeJokeFavoriteStatus(TEST_JOKE) }
        }
    }
}