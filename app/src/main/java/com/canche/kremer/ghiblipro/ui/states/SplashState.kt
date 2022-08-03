package com.canche.kremer.ghiblipro.ui.states

import com.canche.kremer.ghiblipro.domain.models.Film

sealed class SplashState{

    object Downloading: SplashState()
    data class ErrorDownloading(val errorMessage: String?): SplashState()
    data class SuccessDownloading(val list: List<Film>): SplashState()
}
