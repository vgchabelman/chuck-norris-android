package br.com.chabelman.data.remote

import rx.Observable

class CategoryApiMock: CategoriesApi {
    companion object {
        val TEST_CATEGORY_LIST = listOf(
            "Test Category 1", "Test Category 2"
        )
    }

    override fun getCategoriesList(): Observable<List<String>> {
        return Observable.just(TEST_CATEGORY_LIST)
    }
}