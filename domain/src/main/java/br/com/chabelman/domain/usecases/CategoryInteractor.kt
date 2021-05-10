package br.com.chabelman.domain.usecases

import br.com.chabelman.domain.repository.ICategoriesRepository
import rx.Observable
import javax.inject.Inject

class CategoryInteractor @Inject constructor(
    private val categoriesRepository: ICategoriesRepository
) {

    fun getCategoryList(): Observable<List<String>> {
        return categoriesRepository.getCategories()
    }
}