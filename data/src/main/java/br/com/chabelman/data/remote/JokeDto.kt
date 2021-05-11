package br.com.chabelman.data.remote

import com.google.gson.annotations.SerializedName

data class JokeDto(
    @SerializedName("icon_url") val icon: String,
    val id: String,
    val url: String,
    val value: String
)
