package br.com.chabelman.chucknorrisfacts

import android.app.Application
import br.com.chabelman.chucknorrisfacts.di.DaggerAppComponent

class ChuckApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder().build()
    }
}