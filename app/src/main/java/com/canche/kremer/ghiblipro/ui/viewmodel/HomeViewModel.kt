package com.canche.kremer.ghiblipro.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canche.kremer.ghiblipro.data.network.NetworkResult
import com.canche.kremer.ghiblipro.domain.models.Film
import com.canche.kremer.ghiblipro.domain.usecases.GetFilmsUseCase
import com.canche.kremer.ghiblipro.domain.usecases.SaveFilmsUseCase
import com.canche.kremer.ghiblipro.domain.usecases.SearchFilmUseCase
import com.canche.kremer.ghiblipro.ui.UiState
import com.canche.kremer.ghiblipro.ui.states.HomeState
import com.canche.kremer.ghiblipro.ui.states.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel@Inject constructor(private val getFilmsUseCase: GetFilmsUseCase,
                                       private val  searchFilmUseCase: SearchFilmUseCase,
                                       private val saveFilmsUseCase: SaveFilmsUseCase,
) : ViewModel() {

    private var _uiState = MutableLiveData<HomeState>()
    val uiState: LiveData<HomeState> get() = _uiState

    init { getFilmsFromDB() }

    private fun getFilmsFromDB(){
    viewModelScope.launch{
        _uiState.value = HomeState.LoadFilms(getFilmsUseCase.fromDB())
    }
    }

    private fun getFilmsFromApi(){
        viewModelScope.launch {
            when(val result = getFilmsUseCase.fromApi()){
                is NetworkResult.Success -> saveFilms(result.data)
                is NetworkResult.Error -> _uiState.value = HomeState.ErrorDownloading(result.message)
                is NetworkResult.Exception -> _uiState.value = HomeState.ErrorDownloading(result.e.message)
            }
        }
    }

    private suspend fun saveFilms(films: List<Film>){
        saveFilmsUseCase(films)
        _uiState.value = HomeState.LoadFilms(films)
    }

    fun searchFilm(string: String){
        viewModelScope.launch{
            _uiState.value = HomeState.LoadFilms(searchFilmUseCase(string))
        }
    }

    fun onRefresh(){
        getFilmsFromApi()
    }

    fun onFilmSelected(film: Film){
        _uiState.value = HomeState.Navigate(film)
    }

    fun setWating(){
        _uiState.value = HomeState.Waiting
    }

}