package br.com.chabelman.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface JokeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(jokeEntity: JokeEntity)

    @Delete
    fun delete(jokeEntity: JokeEntity)

    @Query("SELECT * FROM JokeEntity WHERE id = :id")
    fun getJoke(id: String): JokeEntity?

    @Query("SELECT * FROM JokeEntity")
    fun getAllJoke(): List<JokeEntity>

    @Query("SELECT * FROM JokeEntity WHERE value LIKE '%' || :query || '%'")
    fun searchJoke(query: String): List<JokeEntity>
}