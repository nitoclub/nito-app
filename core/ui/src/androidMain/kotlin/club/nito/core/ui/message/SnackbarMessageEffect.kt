package club.nito.core.ui.message

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

/**
 * SnackbarMessageEffect shows a snackbar message when a [UserMessage] is emitted by [userMessageStateHolder].
 */
@Composable
fun SnackbarMessageEffect(
    snackbarHostState: SnackbarHostState,
    userMessageStateHolder: UserMessageStateHolder,
) {
    userMessageStateHolder.messageUiState.userMessages.firstOrNull()?.let { userMessage ->
        LaunchedEffect(userMessage) {
            val snackbarResult: SnackbarResult = if (userMessage.duration == null) {
                snackbarHostState.showSnackbar(
                    message = userMessage.message,
                    actionLabel = userMessage.actionLabel,
                )
            } else {
                snackbarHostState.showSnackbar(
                    message = userMessage.message,
                    actionLabel = userMessage.actionLabel,
                    duration = userMessage.duration,
                )
            }
            userMessageStateHolder.messageShown(
                messageId = userMessage.id,
                userMessageResult = when (snackbarResult) {
                    SnackbarResult.Dismissed -> UserMessageResult.Dismissed
                    SnackbarResult.ActionPerformed -> UserMessageResult.ActionPerformed
                },
            )
        }
    }
}
