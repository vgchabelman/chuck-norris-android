package br.com.chabelman.domain.repository

import rx.Observable

class CategoriesRepositoryMock: ICategoriesRepository {
    companion object {
        val TEST_CATEGORY_LIST = listOf("TEST_CATEGORY")
    }

    override fun getCategories(): Observable<List<String>> {
        return Observable.just(TEST_CATEGORY_LIST)
    }
}