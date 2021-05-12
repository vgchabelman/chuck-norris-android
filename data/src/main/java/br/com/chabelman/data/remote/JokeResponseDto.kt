package br.com.chabelman.data.remote

data class JokeResponseDto(
    val total: Int,
    val result: List<JokeDto>
)