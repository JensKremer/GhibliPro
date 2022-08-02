package com.canche.kremer.ghiblipro.ui.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.canche.kremer.ghiblipro.data.network.GhibliApi
import com.canche.kremer.ghiblipro.data.network.NetworkResult
import com.canche.kremer.ghiblipro.domain.models.Film
import com.canche.kremer.ghiblipro.domain.usecases.GetFilmsUseCase
import com.canche.kremer.ghiblipro.domain.usecases.SaveFilmsUseCase
import com.canche.kremer.ghiblipro.domain.usecases.SearchFilmUseCase
import com.canche.kremer.ghiblipro.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GhibliViewModel @Inject constructor(private val getFilmsUseCase: GetFilmsUseCase,
                                          private val searchFilmUseCase: SearchFilmUseCase,
                                          private val saveFilmsUseCase: SaveFilmsUseCase,
                                          ) : ViewModel() {

    private var _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

   private var _films = MutableLiveData<List<Film>>()
           val films: LiveData<List<Film>> get() = _films

    private var _filmSelected = MutableLiveData<Film>()
    val filmSelect: LiveData<Film> get() = _filmSelected

    init { getFilmsFromApi() }

    fun onFilmSelected(film: Film){
        _filmSelected.value = film
        _uiState.value = UiState.Navigation
    }


    private fun getFilmsFromApi(){
        viewModelScope.launch {
            when(val result = getFilmsUseCase()){
                is NetworkResult.Success -> saveFilms(result.data)
                is NetworkResult.Error -> Log.d("Api Error: ${result.code}", result.message.toString())
                is NetworkResult.Exception -> Log.d("Api Error", result.e.message.toString())
            }

        }
    }

    private suspend fun saveFilms(films: List<Film>){
        saveFilmsUseCase(films)
        _films.postValue(films)
        _uiState.value = UiState.Content
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