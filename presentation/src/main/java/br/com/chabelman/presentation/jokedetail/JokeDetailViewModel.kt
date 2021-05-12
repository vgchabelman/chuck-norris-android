package br.com.chabelman.presentation.jokedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.chabelman.domain.model.JokeBo
import br.com.chabelman.domain.usecases.JokeInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rx.schedulers.Schedulers
import javax.inject.Inject

class JokeDetailViewModel: ViewModel() {
    @Inject
    lateinit var jokeInteractor: JokeInteractor

    val joke: MutableLiveData<JokeBo> by lazy { MutableLiveData() }

    fun getRandomJoke(category: String) {
        viewModelScope.launch {
            jokeInteractor.getRandomJoke(category)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    joke.postValue(it)
                }
        }
    }

    fun saveFavoriteStatus(jokeBo: JokeBo) {
        viewModelScope.launch(Dispatchers.IO) {
            jokeInteractor.changeJokeFavoriteStatus(jokeBo)
        }
    }
}