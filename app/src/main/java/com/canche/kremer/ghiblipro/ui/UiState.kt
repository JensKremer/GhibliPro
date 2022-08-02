package com.canche.kremer.ghiblipro.ui

import com.canche.kremer.ghiblipro.domain.models.Film

sealed class UiState {
    object Loading : UiState()
    object Content : UiState()
    object Navigation : UiState()
}