package club.nito.feature.top

import club.nito.core.model.FetchSingleResult
import club.nito.core.model.Schedule

data class TopScreenUiState(
    val recentSchedule: FetchSingleResult<Schedule>,
)
