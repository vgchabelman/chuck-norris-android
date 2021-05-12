package br.com.chabelman.presentation.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.chabelman.domain.model.JokeBo
import br.com.chabelman.domain.usecases.JokeInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rx.schedulers.Schedulers
import javax.inject.Inject

class SearchViewModel: ViewModel() {
    @Inject
    lateinit var interactor: JokeInteractor

    val jokeList: MutableLiveData<List<JokeBo>> by lazy { MutableLiveData() }

    fun searchJokes(query: String) {
        viewModelScope.launch {
            interactor.searchJokes(query)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    jokeList.postValue(it)
                }
        }
    }

    fun saveFavoriteStatus(jokeBo: JokeBo) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.changeJokeFavoriteStatus(jokeBo)
        }
    }
}