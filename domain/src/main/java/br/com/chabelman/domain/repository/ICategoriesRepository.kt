package br.com.chabelman.domain.repository

import rx.Observable

interface ICategoriesRepository {
    fun getCategories(): Observable<List<String>>
}