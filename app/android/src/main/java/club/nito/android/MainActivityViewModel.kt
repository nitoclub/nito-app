package club.nito.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import club.nito.core.domain.ObserveAuthStatusUseCase
import club.nito.core.model.AuthStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    observeAuthStatusUseCase: ObserveAuthStatusUseCase,
) : ViewModel() {
    val uiState: StateFlow<MainActivityUiState> = observeAuthStatusUseCase().map {
        when (it) {
            is AuthStatus.Authenticated -> MainActivityUiState.Success(it)
            AuthStatus.Loading -> MainActivityUiState.Loading
            AuthStatus.NetworkError -> MainActivityUiState.Loading
            AuthStatus.NotAuthenticated -> MainActivityUiState.Success(it)
        }
    }.stateIn(
        scope = viewModelScope,
        initialValue = MainActivityUiState.Loading,
        started = SharingStarted.WhileSubscribed(5_000),
    )
}
