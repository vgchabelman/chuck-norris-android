package br.com.chabelman.data.repository

import br.com.chabelman.data.remote.CategoriesApi
import br.com.chabelman.domain.repository.ICategoriesRepository
import rx.Observable
import javax.inject.Inject

class CategoriesRepository @Inject constructor(
    private val categoriesApi: CategoriesApi
): ICategoriesRepository {

    override fun getCategories(): Observable<List<String>> {
        return categoriesApi.getCategoriesList()
    }
}