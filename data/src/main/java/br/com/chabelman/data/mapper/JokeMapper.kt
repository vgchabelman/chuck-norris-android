package br.com.chabelman.data.mapper

import br.com.chabelman.data.local.JokeEntity
import br.com.chabelman.data.remote.JokeDto
import br.com.chabelman.domain.model.JokeBo

fun JokeDto.toJokeBO(): JokeBo {
    return JokeBo(
        icon = icon,
        id = id,
        url = url,
        value = value
    )
}

fun JokeBo.toJokeEntity(category: String? = null): JokeEntity {
    return JokeEntity(
        id = id,
        url = url,
        value = value,
        category = category
    )
}

fun JokeEntity.toJokeBo(): JokeBo {
    return JokeBo(
        id = id,
        url = url,
        value = value,
        icon = "",
        isFavorite = true
    )
}