package br.com.chabelman.chucknorrisfacts.di

import android.app.Application
import android.content.Context
import br.com.chabelman.chucknorrisfacts.ChuckApplication
import br.com.chabelman.data.local.ChuckDatabase
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RetrofitModule::class,
        RoomModule::class,
        AppModule::class
    ]
)
interface AppComponent {
    val context: Context
    val retrofit: Retrofit
    val chuckDatabase: ChuckDatabase

    fun inject(chuckApplication: ChuckApplication)
}