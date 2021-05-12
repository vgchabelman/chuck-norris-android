package br.com.chabelman.presentation.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.chabelman.domain.model.JokeBo
import br.com.chabelman.domain.usecases.CategoryInteractor
import br.com.chabelman.domain.usecases.JokeInteractor
import kotlinx.coroutines.launch
import rx.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel : ViewModel() {
    @Inject
    lateinit var jokeInteractor: JokeInteractor

    @Inject
    lateinit var categoriesInteractor: CategoryInteractor

    val randomJokeBo: MutableLiveData<JokeBo> by lazy { MutableLiveData() }
    val categoryList: MutableLiveData<List<String>> by lazy { MutableLiveData() }

    fun getRandomJoke() {
        viewModelScope.launch {
            jokeInteractor.getRandomJoke()
                .subscribeOn(Schedulers.io())
                .doOnError {
                    Log.e("chuckError", it.message, it)
                }
                .subscribe { jokeBo ->
                    randomJokeBo.postValue(jokeBo)
                }
        }
    }

    fun updateCategoryList() {
        viewModelScope.launch {
            categoriesInteractor.getCategoryList()
                .subscribeOn(Schedulers.io())
                .subscribe {
                    categoryList.postValue(it)
                }
        }
    }
}