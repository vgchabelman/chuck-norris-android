package br.com.chabelman.chucknorrisfacts.di

import android.content.Context
import androidx.room.Room
import br.com.chabelman.data.local.ChuckDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context): ChuckDatabase {
        return Room.databaseBuilder(
            context,
            ChuckDatabase::class.java,
            "chuck-database"
        ).build()
    }
}