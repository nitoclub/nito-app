package club.nito.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import club.nito.core.domain.ModifyPasswordUseCase
import club.nito.core.domain.ObserveAuthStatusUseCase
import club.nito.core.domain.SignOutUseCase
import club.nito.core.model.AuthStatus
import club.nito.core.model.ExecuteResult
import club.nito.core.model.FetchSingleResult
import club.nito.core.ui.buildUiState
import club.nito.core.ui.message.UserMessageStateHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
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
    private val modifyPasswordUseCase: ModifyPasswordUseCase,
    private val signOutUseCase: SignOutUseCase,
    val userMessageStateHolder: UserMessageStateHolder,
) : ViewModel(),
    UserMessageStateHolder by userMessageStateHolder {

    private val authStatus = observeAuthStatusUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FetchSingleResult.Loading,
    )

    private val showModifyPasswordDialog = MutableStateFlow(false)
    private val newPassword = MutableStateFlow("")
    private val isPasswordModifying = MutableStateFlow(false)

    val uiState: StateFlow<SettingsScreenUiState> = buildUiState(
        authStatus,
        showModifyPasswordDialog,
        newPassword,
        isPasswordModifying,
    ) { authStatus, showModifyPasswordDialog, newPassword, isPasswordModifying ->
        val modifyPassword: ModifyPasswordUiState = when {
            isPasswordModifying -> ModifyPasswordUiState.Show.Modifying(newPassword)
            showModifyPasswordDialog -> ModifyPasswordUiState.Show.Input(newPassword)
            else -> ModifyPasswordUiState.Idle
        }

        SettingsScreenUiState(
            isSignOuting = authStatus is FetchSingleResult.Loading,
            modifyPassword = modifyPassword,
        )
    }

    private val _events = MutableStateFlow<List<SettingsEvent>>(emptyList())
    val event: Flow<SettingsEvent?> = _events.map { it.firstOrNull() }

    init {
        viewModelScope.launch {
            authStatus.collectLatest {
                if (it is FetchSingleResult.Success && it.data is AuthStatus.NotAuthenticated) {
                    _events.emit(listOf(SettingsEvent.SignedOut))
                }
            }
        }
    }

    fun dispatch(intent: SettingsIntent) {
        viewModelScope.launch {
            when (intent) {
                SettingsIntent.ClickShowModifyPasswordDialog -> showModifyPasswordDialog.emit(true)
                is SettingsIntent.ChangeNewPasswordValue -> newPassword.emit(intent.newValue)
                SettingsIntent.ClickModifyPassword -> {
                    when (modifyPasswordUseCase(newPassword.value)) {
                        is ExecuteResult.Success -> {
                            val showMessage = async { userMessageStateHolder.showMessage("パスワードを変更しました") }
                            newPassword.emit("")
                            showModifyPasswordDialog.emit(false)
                            showMessage.await()
                        }

                        is ExecuteResult.Failure -> userMessageStateHolder.showMessage("パスワードの変更に失敗しました")
                    }
                }

                SettingsIntent.ClickDismissModifyPasswordDialog -> showModifyPasswordDialog.emit(false)
                SettingsIntent.ClickSignOut -> {
                    val result = signOutUseCase()
                    if (result is ExecuteResult.Failure) {
                        userMessageStateHolder.showMessage("サインアウトに失敗しました")
                    }
                }
            }
        }
    }

    fun consume(event: SettingsEvent) {
        viewModelScope.launch {
            _events.emit(_events.value.filterNot { it == event })
        }
    }
}
