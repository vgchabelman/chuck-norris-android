package br.com.chabelman.domain

import br.com.chabelman.domain.repository.CategoriesRepositoryMock
import br.com.chabelman.domain.usecases.CategoryInteractor
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CategoryInteractorTest {
    lateinit var interactor: CategoryInteractor

    @Before
    fun setup() {
        interactor = CategoryInteractor(CategoriesRepositoryMock())
    }

    @Test
    fun `it should return and observable with the category list`() {
        val categoryList: MutableList<String> = mutableListOf()

        interactor.getCategoryList()
            .subscribe {
                categoryList.addAll(it)
            }
        Assert.assertEquals(CategoriesRepositoryMock.TEST_CATEGORY_LIST, categoryList)
    }
}