package br.com.chabelman.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class JokeEntity(
    @PrimaryKey
    val id: String,
    val url: String,
    val value: String,
    val category: String
)