package br.com.chabelman.data.remote

import br.com.chabelman.data.CATEGORIES
import retrofit2.http.GET
import rx.Observable

interface CategoriesApi {
    @GET(CATEGORIES)
    fun getCategoriesList(): Observable<List<String>>
}