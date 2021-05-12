package br.com.chabelman.chucknorrisfacts.di

import br.com.chabelman.presentation.favorites.FavoritesViewModel
import dagger.Component

@Component(
    modules = [
        JokeModule::class
    ],
    dependencies = [
        AppComponent::class
    ]
)
@ActivityScope
interface FavoriteComponent {
    fun inject(viewModel: FavoritesViewModel)
}