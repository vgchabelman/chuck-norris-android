package br.com.chabelman.data.mapper

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