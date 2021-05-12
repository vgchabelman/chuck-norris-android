package br.com.chabelman.domain.model

data class JokeBo(
    val icon: String,
    val id: String,
    val url: String,
    val value: String,
    var isFavorite: Boolean = false
)
