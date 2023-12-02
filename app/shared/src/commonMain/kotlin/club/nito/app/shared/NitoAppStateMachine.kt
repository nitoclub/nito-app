package club.nito.app.shared

import club.nito.core.domain.AuthStatusStreamUseCase
import club.nito.core.model.AuthStatus
import club.nito.core.ui.StateMachine
import club.nito.core.ui.buildUiState
import club.nito.core.ui.stateMachineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class NitoAppStateMachine(
    authStatusStream: AuthStatusStreamUseCase,
) : StateMachine() {
    private val authStatus = authStatusStream().stateIn(
        scope = stateMachineScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = AuthStatus.Loading,
    )

    val uiState: StateFlow<NitoAppUiState> = buildUiState(authStatus) {
        when (it) {
            AuthStatus.Loading -> NitoAppUiState.Loading

            AuthStatus.NotAuthenticated,
            is AuthStatus.Authenticated,
            -> NitoAppUiState.Success(it)

            // TODO: 適切なハンドリングを行う
            AuthStatus.NetworkError -> NitoAppUiState.Loading
        }
    }
}
