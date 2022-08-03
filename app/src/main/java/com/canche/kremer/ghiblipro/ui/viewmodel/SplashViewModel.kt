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
import com.canche.kremer.ghiblipro.ui.states.SplashState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val saveFilmsUseCase: SaveFilmsUseCase,
                                          private val getFilmsUseCase: GetFilmsUseCase,) : ViewModel() {

    private var _uiState = MutableLiveData<SplashState>()
    val uiState: LiveData<SplashState> get() = _uiState

    init { getFilmsFromApi() }

    private fun getFilmsFromApi(){
        _uiState.value = SplashState.Downloading
        viewModelScope.launch {
            when(val result = getFilmsUseCase.fromApi()){
                is NetworkResult.Success -> saveFilms(result.data)
                is NetworkResult.Error -> _uiState.value = SplashState.ErrorDownloading(result.message)
                is NetworkResult.Exception -> _uiState.value = SplashState.ErrorDownloading(result.e.message)
            }

        }
    }


    private suspend fun saveFilms(films: List<Film>){
        saveFilmsUseCase(films)
        _uiState.value = SplashState.SuccessDownloading(films)
    }


}