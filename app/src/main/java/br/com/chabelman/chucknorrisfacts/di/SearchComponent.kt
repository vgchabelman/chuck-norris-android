package br.com.chabelman.chucknorrisfacts.di

import br.com.chabelman.presentation.search.SearchViewModel
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
interface SearchComponent {
    fun inject(viewModel: SearchViewModel)
}