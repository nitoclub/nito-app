package club.nito.feature.auth

import club.nito.core.domain.ObserveAuthStatusUseCase
import club.nito.core.domain.SignInUseCase
import club.nito.core.model.AuthStatus
import club.nito.core.model.ExecuteResult
import club.nito.core.model.FetchSingleResult
import club.nito.core.ui.StateMachine
import club.nito.core.ui.buildUiState
import club.nito.core.ui.message.UserMessageStateHolder
import club.nito.core.ui.stateMachineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginScreenStateMachine internal constructor(
    observeAuthStatusUseCase: ObserveAuthStatusUseCase,
    private val signInUseCase: SignInUseCase,
    val userMessageStateHolder: UserMessageStateHolder,
) : StateMachine(),
    UserMessageStateHolder by userMessageStateHolder {

    private val email = MutableStateFlow("")
    private val password = MutableStateFlow("")
    private val authStatus = observeAuthStatusUseCase().stateIn(
        scope = stateMachineScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FetchSingleResult.Loading,
    )

    val uiState: StateFlow<LoginScreenUiState> = buildUiState(
        email,
        password,
        authStatus,
    ) { email, password, authStatus ->
        LoginScreenUiState(
            email = email,
            password = password,
            isSignInning = authStatus is FetchSingleResult.Loading,
        )
    }

    private val _events = MutableStateFlow<List<LoginScreenEvent>>(emptyList())
    val event: Flow<LoginScreenEvent?> = _events.map { it.firstOrNull() }

    init {
        stateMachineScope.launch {
            authStatus.collectLatest {
                if (it is FetchSingleResult.Success && it.data is AuthStatus.Authenticated) {
                    _events.emit(listOf(LoginScreenEvent.LoggedIn))
                }
            }
        }
    }

    fun dispatch(intent: LoginScreenIntent) {
        stateMachineScope.launch {
            when (intent) {
                is LoginScreenIntent.ChangeInputEmail -> email.emit(intent.email)
                is LoginScreenIntent.ChangeInputPassword -> password.emit(intent.password)
                LoginScreenIntent.ClickSignIn -> {
                    val result = signInUseCase(email.value, password.value)
                    if (result is ExecuteResult.Failure) {
                        userMessageStateHolder.showMessage("ログインに失敗しました")
                    }
                }

                LoginScreenIntent.ClickRegister -> {}
            }
        }
    }

    fun consume(event: LoginScreenEvent) {
        stateMachineScope.launch {
            _events.emit(_events.value.filterNot { it == event })
        }
    }
}
