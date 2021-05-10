package br.com.chabelman.chucknorrisfacts.di

import br.com.chabelman.data.remote.CategoriesApi
import br.com.chabelman.data.repository.CategoriesRepository
import br.com.chabelman.domain.repository.ICategoriesRepository
import br.com.chabelman.domain.usecases.CategoryInteractor
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class CategoryModule {

    @Provides
    fun provideCategoriesService(retrofit: Retrofit): CategoriesApi {
        return retrofit.create(CategoriesApi::class.java)
    }

    @Provides
    fun provideCategoryRepository(categoriesApi: CategoriesApi): ICategoriesRepository {
        return CategoriesRepository(categoriesApi)
    }

    @Provides
    fun provideCategoryInteractor(categoryRepository: ICategoriesRepository): CategoryInteractor {
        return CategoryInteractor(categoryRepository)
    }
}