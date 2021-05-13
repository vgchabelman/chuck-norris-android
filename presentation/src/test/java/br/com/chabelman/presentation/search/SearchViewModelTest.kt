package br.com.chabelman.presentation.search

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
class SearchViewModelTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var viewModel: SearchViewModel

    @MockK
    var interactor = mockk<JokeInteractor>()


    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        viewModel = SearchViewModel()
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel.interactor = interactor
    }

    @Test
    fun `it should update joke list from interactor`() {
        val testQuery = "test query"
        every {
            interactor.searchJokes(testQuery)
        } returns Observable.just(listOf(TEST_JOKE))

        runBlockingTest {
            viewModel.searchJokes(testQuery)
            Thread.sleep(10)
            Assert.assertEquals(listOf(TEST_JOKE), viewModel.jokeList.value)
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