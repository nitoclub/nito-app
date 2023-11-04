package club.nito.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import club.nito.core.domain.ObserveAuthStatusUseCase
import club.nito.core.model.FetchSingleResult
import club.nito.core.ui.buildUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    observeAuthStatusUseCase: ObserveAuthStatusUseCase,
) : ViewModel() {
    private val authStatus = observeAuthStatusUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FetchSingleResult.Loading,
    )

    val uiState: StateFlow<MainActivityUiState> = buildUiState(authStatus) {
        if (it !is FetchSingleResult.Success) {
            return@buildUiState MainActivityUiState.Loading
        }

        MainActivityUiState.Success(it.data)
    }
}
