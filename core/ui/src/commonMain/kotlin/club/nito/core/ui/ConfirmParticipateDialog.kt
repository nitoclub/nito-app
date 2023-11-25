package club.nito.core.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import club.nito.core.common.NitoDateFormatter
import club.nito.core.designsystem.component.Text
import club.nito.core.domain.model.ParticipantSchedule

@Composable
public fun ConfirmParticipateDialog(
    schedule: ParticipantSchedule,
    dateTimeFormatter: NitoDateFormatter,
    onParticipateRequest: (schedule: ParticipantSchedule) -> Unit,
    onDismissRequest: () -> Unit,
) {
    AlertDialog(
        title = {
            Text(
                text = "参加登録",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
            )
        },
        text = {
            Text(
                text = "${dateTimeFormatter.formatDateTime(schedule.scheduledAt)} 集合のトランポリンに参加しますか？",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        },
        onDismissRequest = onDismissRequest,
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(
                    text = "キャンセル",
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        },
        confirmButton = {
            TextButton(onClick = { onParticipateRequest(schedule) }) {
                Text(
                    text = "参加する",
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        },
    )
}
