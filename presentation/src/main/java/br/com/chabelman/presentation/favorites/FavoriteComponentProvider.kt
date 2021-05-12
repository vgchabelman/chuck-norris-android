package br.com.chabelman.presentation.favorites

fun interface FavoriteComponentProvider {
    fun injectFavoritesViewModel(viewModel: FavoritesViewModel)
}