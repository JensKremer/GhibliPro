package com.canche.kremer.ghiblipro.ui.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.canche.kremer.ghiblipro.data.network.GhibliApi
import com.canche.kremer.ghiblipro.data.network.NetworkResult
import com.canche.kremer.ghiblipro.domain.models.Film
import com.canche.kremer.ghiblipro.domain.usecases.GetFilmsUseCase
import com.canche.kremer.ghiblipro.domain.usecases.SearchFilmUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GhibliViewModel @Inject constructor(private val getFilmsUseCase: GetFilmsUseCase, private val searchFilmUseCase: SearchFilmUseCase) : ViewModel() {

   private var _films = MutableLiveData<List<Film>>()
           val films: LiveData<List<Film>> get() = _films

    private var _filmSelected = MutableLiveData<Film>()
    val filmSelect: LiveData<Film> get() = _filmSelected

    init {getFilmsFromApi()}

    fun setSelectedFilm(film: Film){
        this._filmSelected.value = film
    }

    private fun getFilmsFromApi(){
        viewModelScope.launch {
            when(val result = getFilmsUseCase()){
                is NetworkResult.Success -> _films.postValue(result.data)
                is NetworkResult.Error -> Log.d("Api Error: ${result.code}", result.message.toString())
                is NetworkResult.Exception -> Log.d("Api Error", result.e.message.toString())
            }

        }
    }

    fun onRefresh(){
        getFilmsFromApi()
    }

     fun searchFilm(string: String){
        viewModelScope.launch{
        _films.postValue(searchFilmUseCase(string))
        }
    }

}