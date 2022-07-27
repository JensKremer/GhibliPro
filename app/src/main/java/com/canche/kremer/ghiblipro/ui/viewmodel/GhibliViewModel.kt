package com.canche.kremer.ghiblipro.ui.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.canche.kremer.ghiblipro.data.network.GhibliApi
import com.canche.kremer.ghiblipro.domain.models.Film
import com.canche.kremer.ghiblipro.domain.usecases.GetFilmsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GhibliViewModel @Inject constructor(private val getFilmsUseCase: GetFilmsUseCase) : ViewModel() {

   private var _films = MutableLiveData<List<Film>>()
           val films: LiveData<List<Film>> get() = _films

    private var _filmSelected = MutableLiveData<Film>()
    val filmSelect: LiveData<Film> get() = _filmSelected

    init {
        viewModelScope.launch {
            val result = getFilmsUseCase()
            if(!result.isNullOrEmpty()){
                _films.postValue(result)
            }
        }
    }

    fun setSelectedFilm(film: Film){
        this._filmSelected.value = film
    }

    val onRefresh = {
      _films.value = emptyList()
        Log.d("Refresh", "is refreshing")
    }



}