package br.com.chabelman.chucknorrisfacts.di

import br.com.chabelman.presentation.jokedetail.JokeDetailViewModel
import dagger.Component

@Component(
    dependencies = [
        AppComponent::class
    ],
    modules = [
        JokeModule::class
    ]
)
@ActivityScope
interface JokeDetailComponent {
    fun inject(viewModel: JokeDetailViewModel)
}