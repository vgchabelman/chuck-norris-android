package br.com.chabelman.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface JokeDao {
    @Insert
    fun insert(jokeEntity: JokeEntity)

    @Delete
    fun delete(jokeEntity: JokeEntity)

    @Query("SELECT * FROM JokeEntity WHERE id = :id")
    fun getJoke(id: String): JokeEntity?

    @Query("SELECT * FROM JokeEntity")
    fun getAllJoke(): List<JokeEntity>
}