package br.com.chabelman.chucknorrisfacts.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RetrofitModule::class,
        JokeModule::class,
        CategoryModule::class,
    ]
)
interface AppComponent {
}