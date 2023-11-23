package club.nito.feature.settings

import club.nito.core.domain.LogoutUseCase
import club.nito.core.domain.ModifyPasswordUseCase
import club.nito.core.domain.ObserveAuthStatusUseCase
import club.nito.core.model.AuthStatus
import club.nito.core.model.ExecuteResult
import club.nito.core.model.FetchSingleResult
import club.nito.core.ui.StateMachine
import club.nito.core.ui.buildUiState
import club.nito.core.ui.message.UserMessageStateHolder
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.viewModelScope

public class SettingsScreenStateMachine(
    observeAuthStatus: ObserveAuthStatusUseCase,
    private val modifyPassword: ModifyPasswordUseCase,
    private val logout: LogoutUseCase,
    public val userMessageStateHolder: UserMessageStateHolder,
) : StateMachine(),
    UserMessageStateHolder by userMessageStateHolder {

    private val authStatus = observeAuthStatus().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FetchSingleResult.Loading,
    )

    private val showModifyPasswordDialog = MutableStateFlow(false)
    private val newPassword = MutableStateFlow("")
    private val isPasswordModifying = MutableStateFlow(false)

    public val uiState: StateFlow<SettingsScreenUiState> = buildUiState(
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

    private val _events = MutableStateFlow<List<SettingsScreenEvent>>(emptyList())
    public val event: Flow<SettingsScreenEvent?> = _events.map { it.firstOrNull() }

    init {
        viewModelScope.launch {
            authStatus.collectLatest {
                if (it is FetchSingleResult.Success && it.data is AuthStatus.NotAuthenticated) {
                    _events.emit(listOf(SettingsScreenEvent.SignedOut))
                }
            }
        }
    }

    public fun dispatch(intent: SettingsScreenIntent) {
        viewModelScope.launch {
            when (intent) {
                SettingsScreenIntent.ClickShowModifyPasswordDialog -> showModifyPasswordDialog.emit(true)
                is SettingsScreenIntent.ChangeNewPasswordValue -> newPassword.emit(intent.newValue)
                SettingsScreenIntent.ClickModifyPassword -> {
                    when (modifyPassword(newPassword.value)) {
                        is ExecuteResult.Success -> {
                            val showMessage = async { userMessageStateHolder.showMessage("パスワードを変更しました") }
                            newPassword.emit("")
                            showModifyPasswordDialog.emit(false)
                            showMessage.await()
                        }

                        is ExecuteResult.Failure -> userMessageStateHolder.showMessage("パスワードの変更に失敗しました")
                    }
                }

                SettingsScreenIntent.ClickDismissModifyPasswordDialog -> showModifyPasswordDialog.emit(false)
                SettingsScreenIntent.ClickSignOut -> {
                    val result = logout()
                    if (result is ExecuteResult.Failure) {
                        userMessageStateHolder.showMessage("サインアウトに失敗しました")
                    }
                }
            }
        }
    }

    public fun consume(event: SettingsScreenEvent) {
        viewModelScope.launch {
            _events.emit(_events.value.filterNot { it == event })
        }
    }
}
