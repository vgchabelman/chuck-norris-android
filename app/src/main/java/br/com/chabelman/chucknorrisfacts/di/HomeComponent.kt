package br.com.chabelman.chucknorrisfacts.di

import br.com.chabelman.presentation.home.HomeViewModel
import dagger.Component

@Component(
    dependencies = [
        AppComponent::class
    ],
    modules = [
        JokeModule::class,
        CategoryModule::class
    ]
)
@ActivityScope
interface HomeComponent {
    fun inject(viewModel: HomeViewModel)
}