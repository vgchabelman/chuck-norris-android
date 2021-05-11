package br.com.chabelman.chucknorrisfacts.di

import br.com.chabelman.chucknorrisfacts.ChuckApplication
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RetrofitModule::class,
    ]
)
interface AppComponent {
    val retrofit: Retrofit

    fun inject(chuckApplication: ChuckApplication)
}