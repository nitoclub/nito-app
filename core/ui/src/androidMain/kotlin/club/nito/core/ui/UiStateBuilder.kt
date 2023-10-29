package club.nito.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

fun <T1, R> ViewModel.buildUiState(
    flow: StateFlow<T1>,
    transform: (T1) -> R,
): StateFlow<R> = flow.map(transform = transform)
    .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = transform(
            flow.value,
        ),
    )
