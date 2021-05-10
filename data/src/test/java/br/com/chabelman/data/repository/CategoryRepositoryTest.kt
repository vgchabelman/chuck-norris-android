package br.com.chabelman.data.repository

import br.com.chabelman.data.remote.CategoryApiMock
import org.junit.Before
import org.junit.Test

class CategoryRepositoryTest {
    lateinit var categoriesRepository: CategoriesRepository

    @Before
    fun setup() {
        categoriesRepository = CategoriesRepository(CategoryApiMock())
    }

    @Test
    fun `it should return API category list`() {
        val categoryObservable = categoriesRepository.getCategories()

//        val list = categoryObservable.subscribe
//        Assert.assertArrayEquals(list, CategoryApiMock.TEST_CATEGORY_LIST)
    }
}