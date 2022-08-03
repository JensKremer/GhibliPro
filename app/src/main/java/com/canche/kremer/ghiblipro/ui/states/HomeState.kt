package com.canche.kremer.ghiblipro.ui.states

import com.canche.kremer.ghiblipro.domain.models.Film

sealed class HomeState {
    object Waiting :HomeState()
    data class LoadFilms(val films: List<Film>): HomeState()
    data class ErrorDownloading(val errorMessage: String?): HomeState()
    data class Navigate(val film: Film): HomeState()

}
