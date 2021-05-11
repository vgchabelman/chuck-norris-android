package br.com.chabelman.chucknorrisfacts

import android.app.Application
import br.com.chabelman.chucknorrisfacts.di.AppComponent
import br.com.chabelman.chucknorrisfacts.di.DaggerAppComponent
import br.com.chabelman.chucknorrisfacts.di.DaggerHomeComponent
import br.com.chabelman.presentation.home.HomeComponentProvider
import br.com.chabelman.presentation.home.HomeViewModel

class ChuckApplication : Application(), HomeComponentProvider {
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
}