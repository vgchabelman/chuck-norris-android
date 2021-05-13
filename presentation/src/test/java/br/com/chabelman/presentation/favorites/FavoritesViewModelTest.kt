package br.com.chabelman.presentation.favorites

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.chabelman.domain.usecases.JokeInteractor
import br.com.chabelman.presentation.TEST_JOKE
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
class FavoritesViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var viewModel: FavoritesViewModel

    @MockK
    var interactor = mockk<JokeInteractor>()

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        viewModel = FavoritesViewModel()
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel.interactor = interactor
    }

    @Test
    fun `it should get favorite list from interactor`() {
        every {
            interactor.getFavoriteJokes()
        } returns Observable.just(listOf(TEST_JOKE))

        runBlockingTest {
            viewModel.updateFavoritesList()
            Thread.sleep(50)
            Assert.assertEquals(listOf(TEST_JOKE), viewModel.favoritesList.value)
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