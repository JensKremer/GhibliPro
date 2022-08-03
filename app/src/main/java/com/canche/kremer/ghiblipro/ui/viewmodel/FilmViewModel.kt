package com.canche.kremer.ghiblipro.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canche.kremer.ghiblipro.domain.models.Film
import com.canche.kremer.ghiblipro.domain.usecases.GetFilmsUseCase
import com.canche.kremer.ghiblipro.ui.states.HomeState
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmViewModel@Inject constructor(private val getFilmsUseCase: GetFilmsUseCase): ViewModel() {

    private var _film = MutableLiveData<Film>()
    val film: LiveData<Film> get() = _film

     fun getFilmById(id: String){
        viewModelScope.launch{
            _film.value = getFilmsUseCase.byId(id)
        }
    }
}