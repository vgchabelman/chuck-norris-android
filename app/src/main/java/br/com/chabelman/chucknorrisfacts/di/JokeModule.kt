package br.com.chabelman.chucknorrisfacts.di

import br.com.chabelman.data.remote.JokesApi
import br.com.chabelman.data.repository.JokesRepository
import br.com.chabelman.domain.repository.IJokesRepository
import br.com.chabelman.domain.usecases.JokeInteractor
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class JokeModule {

    @Provides
    fun provideJokesService(retrofit: Retrofit): JokesApi {
        return retrofit.create(JokesApi::class.java)
    }

    @Provides
    fun provideJokeRepository(jokesApi: JokesApi): IJokesRepository {
        return JokesRepository(jokesApi)
    }

    @Provides
    fun provideJokeInteractor(jokesRepository: IJokesRepository): JokeInteractor {
        return JokeInteractor(jokesRepository)
    }
}