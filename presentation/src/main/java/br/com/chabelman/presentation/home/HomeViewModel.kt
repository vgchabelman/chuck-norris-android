package br.com.chabelman.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.chabelman.domain.model.JokeBo
import br.com.chabelman.domain.usecases.CategoryInteractor
import br.com.chabelman.domain.usecases.JokeInteractor
import kotlinx.coroutines.launch
import rx.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel: ViewModel() {
    @Inject
    lateinit var jokeInteractor: JokeInteractor
    @Inject
    lateinit var categoriesInteractor: CategoryInteractor

    val randomJokeBo: MutableLiveData<JokeBo> by lazy { MutableLiveData() }

    fun getRandomJoke() {
        viewModelScope.launch {
            jokeInteractor.getRandomJoke()
                .subscribeOn(Schedulers.io())
                .subscribe { jokeBo ->
                    randomJokeBo.postValue(jokeBo)
                }
        }
    }
}