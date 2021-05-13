package br.com.chabelman.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.chabelman.domain.usecases.CategoryInteractor
import br.com.chabelman.domain.usecases.JokeInteractor
import br.com.chabelman.presentation.CATEGORY_TEST
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
import org.junit.Test

import org.junit.Rule
import org.junit.rules.TestRule
import rx.Observable

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    lateinit var viewModel: HomeViewModel

    @MockK
    var jokeInteractor = mockk<JokeInteractor>()

    @MockK
    var categoryInteractor = mockk<CategoryInteractor>()

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        viewModel = HomeViewModel()
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel.jokeInteractor = jokeInteractor
        viewModel.categoriesInteractor = categoryInteractor
    }

    @Test
    fun `it should get random joke from interactor`() {
        every {
            jokeInteractor.getRandomJoke()
        } returns Observable.just(TEST_JOKE)

        runBlockingTest {
            viewModel.getRandomJoke()
            Thread.sleep(50)
            Assert.assertEquals(TEST_JOKE, viewModel.randomJokeBo.value)
        }
    }

    @Test
    fun `it should get categories from interactor`() {
        every {
            categoryInteractor.getCategoryList()
        } returns Observable.just(listOf(CATEGORY_TEST))

        runBlockingTest {
            viewModel.updateCategoryList()
            Thread.sleep(50)
            Assert.assertEquals(listOf(CATEGORY_TEST), viewModel.categoryList.value)
        }
    }

    @Test
    fun `it should call interactor to change favorite status`() {
        runBlockingTest {
            viewModel.saveFavoriteStatus(TEST_JOKE)
            verify(exactly = 1) { jokeInteractor.changeJokeFavoriteStatus(TEST_JOKE) }
        }
    }
}