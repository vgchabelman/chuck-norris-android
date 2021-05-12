package br.com.chabelman.presentation.search

fun interface SearchComponentProvider {
    fun injectSearch(viewModel: SearchViewModel)
}