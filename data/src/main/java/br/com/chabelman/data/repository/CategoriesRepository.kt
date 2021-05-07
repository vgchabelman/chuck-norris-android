package br.com.chabelman.data.repository

import br.com.chabelman.data.remote.CategoriesApi
import rx.Observable
import javax.inject.Inject

class CategoriesRepository @Inject constructor(
    val categoriesApi: CategoriesApi
) {

    fun getCategories(): Observable<List<String>> {
        return categoriesApi.getCategoriesList()
    }
}