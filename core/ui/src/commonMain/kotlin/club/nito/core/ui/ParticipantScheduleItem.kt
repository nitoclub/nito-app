package club.nito.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import club.nito.core.common.NitoDateFormatter
import club.nito.core.designsystem.component.Text
import club.nito.core.domain.model.ParticipantSchedule

@Composable
public fun ParticipantScheduleItem(
    schedule: ParticipantSchedule,
    dateTimeFormatter: NitoDateFormatter,
    modifier: Modifier = Modifier,
    onScheduleClick: (schedule: ParticipantSchedule) -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onScheduleClick(schedule)
            }
            .padding(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp)
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Date",
            )
            Text(
                text = dateTimeFormatter.formatDateTimeString(schedule.scheduledAt),
            )
        }

        ProfileImagesRow(
            profiles = schedule.participants,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        )
    }
}

