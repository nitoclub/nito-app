package club.nito.android

import club.nito.core.model.AuthStatus

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(val authStatus: AuthStatus) : MainActivityUiState
}
