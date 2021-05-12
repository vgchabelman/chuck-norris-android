package br.com.chabelman.chucknorrisfacts

import android.app.Application
import androidx.lifecycle.ViewModel
import br.com.chabelman.chucknorrisfacts.di.AppComponent
import br.com.chabelman.chucknorrisfacts.di.DaggerAppComponent
import br.com.chabelman.chucknorrisfacts.di.DaggerHomeComponent
import br.com.chabelman.chucknorrisfacts.di.DaggerJokeDetailComponent
import br.com.chabelman.presentation.home.HomeComponentProvider
import br.com.chabelman.presentation.home.HomeViewModel
import br.com.chabelman.presentation.jokedetail.JokeDetailComponentProvider
import br.com.chabelman.presentation.jokedetail.JokeDetailViewModel

class ChuckApplication : Application(), HomeComponentProvider, JokeDetailComponentProvider {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()
        appComponent.inject(this)
    }

    override fun injectHomeViewModel(viewModel: HomeViewModel) {
        DaggerHomeComponent
            .builder()
            .appComponent(appComponent)
            .build()
            .inject(viewModel)
    }

    override fun jokeDetailInject(viewModel: JokeDetailViewModel) {
        DaggerJokeDetailComponent
            .builder()
            .appComponent(appComponent)
            .build()
            .inject(viewModel)
    }
}