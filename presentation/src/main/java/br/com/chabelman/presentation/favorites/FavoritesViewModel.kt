package br.com.chabelman.presentation.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.chabelman.domain.model.JokeBo
import br.com.chabelman.domain.usecases.JokeInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import rx.schedulers.Schedulers
import javax.inject.Inject

class FavoritesViewModel : ViewModel() {
    @Inject
    lateinit var interactor: JokeInteractor
    val favoritesList: MutableLiveData<List<JokeBo>> by lazy { MutableLiveData() }

    fun updateFavoritesList() {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getFavoriteJokes()
                .subscribeOn(Schedulers.io())
                .subscribe {
                    favoritesList.postValue(it)
                }
        }
    }

    fun saveFavoriteStatus(jokeBo: JokeBo) {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.changeJokeFavoriteStatus(jokeBo)
        }
    }
}