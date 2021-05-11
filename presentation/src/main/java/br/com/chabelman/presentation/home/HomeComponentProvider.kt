package br.com.chabelman.presentation.home

fun interface HomeComponentProvider {
    fun injectHomeViewModel(viewModel: HomeViewModel)
}