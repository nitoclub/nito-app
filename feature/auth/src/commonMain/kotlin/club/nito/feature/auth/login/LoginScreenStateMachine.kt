package club.nito.feature.auth.login

import club.nito.core.domain.AuthStatusStreamUseCase
import club.nito.core.domain.LoginUseCase
import club.nito.core.model.AuthStatus
import club.nito.core.model.ExecuteResult
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

public class LoginScreenStateMachine(
    authStatusStream: AuthStatusStreamUseCase,
    private val login: LoginUseCase,
    public val userMessageStateHolder: UserMessageStateHolder,
) : StateMachine(),
    UserMessageStateHolder by userMessageStateHolder {

    private val email = MutableStateFlow("")
    private val password = MutableStateFlow("")
    private val isVisiblePassword = MutableStateFlow(false)
    private val authStatus = authStatusStream().stateIn(
        scope = stateMachineScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = AuthStatus.Loading,
    )

    public val uiState: StateFlow<LoginScreenUiState> = buildUiState(
        email,
        password,
        isVisiblePassword,
        authStatus,
    ) { email, password, isVisiblePassword, authStatus ->
        LoginScreenUiState(
            email = email,
            password = password,
            isVisiblePassword = isVisiblePassword,
            isSignInning = authStatus is AuthStatus.Loading,
        )
    }

    private val _events = MutableStateFlow<List<LoginScreenEvent>>(emptyList())
    public val event: Flow<LoginScreenEvent?> = _events.map { it.firstOrNull() }

    init {
        stateMachineScope.launch {
            authStatus.collectLatest {
                if (it is AuthStatus.Authenticated) {
                    _events.emit(listOf(LoginScreenEvent.LoggedIn))
                }
            }
        }
    }

    public fun dispatch(intent: LoginScreenIntent) {
        stateMachineScope.launch {
            when (intent) {
                is LoginScreenIntent.ChangeInputEmail -> email.emit(intent.email)
                is LoginScreenIntent.ChangeInputPassword -> {
                    if (intent.password.length > PASSWORD_MAX_LENGTH) return@launch
                    password.value = intent.password
                }

                LoginScreenIntent.ClickSignIn -> {
                    val result = login(email.value, password.value)
                    if (result is ExecuteResult.Failure) {
                        userMessageStateHolder.showMessage("ログインに失敗しました")
                    }
                }

                LoginScreenIntent.ClickRegister -> {}

                is LoginScreenIntent.ChangePasswordVisible -> isVisiblePassword.value = intent.isPasswordVisible
            }
        }
    }

    public fun consume(event: LoginScreenEvent) {
        stateMachineScope.launch {
            _events.emit(_events.value.filterNot { it == event })
        }
    }

    public companion object {
        private const val PASSWORD_MAX_LENGTH = 32
    }
}
