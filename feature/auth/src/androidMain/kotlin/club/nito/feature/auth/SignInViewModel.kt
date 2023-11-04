package club.nito.feature.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import club.nito.core.domain.ObserveAuthStatusUseCase
import club.nito.core.domain.SignInUseCase
import club.nito.core.model.AuthStatus
import club.nito.core.model.ExecuteResult
import club.nito.core.model.FetchSingleResult
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
class SignInViewModel @Inject constructor(
    observeAuthStatusUseCase: ObserveAuthStatusUseCase,
    private val signInUseCase: SignInUseCase,
    val userMessageStateHolder: UserMessageStateHolder,
) : ViewModel(),
    UserMessageStateHolder by userMessageStateHolder {

    private val email = MutableStateFlow("")
    private val password = MutableStateFlow("")
    private val authStatus = observeAuthStatusUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FetchSingleResult.Loading,
    )

    val uiState: StateFlow<SignInScreenUiState> = buildUiState(
        email,
        password,
        authStatus,
    ) { email, password, authStatus ->
        SignInScreenUiState(
            email = email,
            password = password,
            isSignInning = authStatus is FetchSingleResult.Loading,
        )
    }

    private val _events = MutableStateFlow<List<SignInEvent>>(emptyList())
    val event: Flow<SignInEvent?> = _events.map { it.firstOrNull() }

    init {
        viewModelScope.launch {
            authStatus.collectLatest {
                if (it is FetchSingleResult.Success && it.data is AuthStatus.Authenticated) {
                    _events.emit(listOf(SignInEvent.SignedIn))
                }
            }
        }
    }

    fun dispatch(intent: SignInIntent) {
        viewModelScope.launch {
            when (intent) {
                is SignInIntent.ChangeInputEmail -> email.emit(intent.email)
                is SignInIntent.ChangeInputPassword -> password.emit(intent.password)
                SignInIntent.ClickSignIn -> {
                    val result = signInUseCase(email.value, password.value)
                    if (result is ExecuteResult.Failure) {
                        userMessageStateHolder.showMessage("ログインに失敗しました")
                    }
                }

                SignInIntent.ClickRegister -> {}
            }
        }
    }

    fun consume(event: SignInEvent) {
        viewModelScope.launch {
            _events.emit(_events.value.filterNot { it == event })
        }
    }
}
