package club.nito.app.shared

import club.nito.core.model.AuthStatus

sealed interface NitoAppUiState {
    data object Loading : NitoAppUiState
    data class Success(val authStatus: AuthStatus) : NitoAppUiState
}
