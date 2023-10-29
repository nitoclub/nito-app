package club.nito.feature.top

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import club.nito.core.domain.GetRecentScheduleUseCase
import club.nito.core.model.FetchSingleResult
import club.nito.core.ui.buildUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class TopViewModel @Inject constructor(
    getRecentScheduleUseCase: GetRecentScheduleUseCase,
) : ViewModel() {
    private val recentSchedule = getRecentScheduleUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = FetchSingleResult.Loading,
    )

    val uiState: StateFlow<TopScreenUiState> = buildUiState(recentSchedule) {
        TopScreenUiState(
            recentSchedule = it,
        )
    }
}
