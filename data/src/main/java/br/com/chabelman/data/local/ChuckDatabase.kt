package br.com.chabelman.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        JokeEntity::class
    ],
    version = 1
)
abstract class ChuckDatabase: RoomDatabase() {
    abstract fun jokeDao(): JokeDao
}