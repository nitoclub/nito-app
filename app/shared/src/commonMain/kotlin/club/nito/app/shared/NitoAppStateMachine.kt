package club.nito.app.shared

import club.nito.core.domain.ObserveAuthStatusUseCase
import club.nito.core.model.FetchSingleResult
import club.nito.core.ui.StateMachine
import club.nito.core.ui.buildUiState
import club.nito.core.ui.stateMachineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class NitoAppStateMachine(
    observeAuthStatus: ObserveAuthStatusUseCase,
) : StateMachine() {
    private val authStatus = observeAuthStatus().stateIn(
        scope = stateMachineScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FetchSingleResult.Loading,
    )

    val uiState: StateFlow<NitoAppUiState> = buildUiState(authStatus) {
        if (it !is FetchSingleResult.Success) {
            return@buildUiState NitoAppUiState.Loading
        }

        NitoAppUiState.Success(it.data)
    }
}
