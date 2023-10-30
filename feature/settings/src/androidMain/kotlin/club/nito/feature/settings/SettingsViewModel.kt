package club.nito.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import club.nito.core.domain.ObserveAuthStatusUseCase
import club.nito.core.domain.SignOutUseCase
import club.nito.core.model.AuthStatus
import club.nito.core.ui.buildUiState
import club.nito.core.ui.message.UserMessageStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    observeAuthStatusUseCase: ObserveAuthStatusUseCase,
    private val signOutUseCase: SignOutUseCase,
    val userMessageStateHolder: UserMessageStateHolder,
) : ViewModel(),
    UserMessageStateHolder by userMessageStateHolder {

    private val authStatus = observeAuthStatusUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = AuthStatus.Loading,
    )

    val uiState: StateFlow<SettingsScreenUiState> = buildUiState(
        authStatus,
    ) { authStatus ->
        SettingsScreenUiState(
            isSignOuting = authStatus is AuthStatus.Loading,
        )
    }

    private val _events = MutableStateFlow<List<SettingsEvent>>(emptyList())
    val event: Flow<SettingsEvent?> = _events.map { it.firstOrNull() }

    init {
        viewModelScope.launch {
            authStatus.collectLatest {
                if (it is AuthStatus.NotAuthenticated) {
                    _events.emit(listOf(SettingsEvent.SignedOut))
                }
            }
        }
    }

    fun dispatch(intent: SettingsIntent) {
        viewModelScope.launch {
            when (intent) {
                SettingsIntent.ClickSignOut -> signOutUseCase()
            }
        }
    }

    fun consume(event: SettingsEvent) {
        viewModelScope.launch {
            _events.emit(_events.value.filterNot { it == event })
        }
    }
}
