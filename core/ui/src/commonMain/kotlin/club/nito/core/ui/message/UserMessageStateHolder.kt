package club.nito.core.ui.message

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

public data class MessageUiState(
    val userMessages: List<UserMessage> = emptyList(),
)

public enum class UserMessageResult {
    Dismissed,
    ActionPerformed,
}

public interface UserMessageStateHolder {
    public val messageUiState: MessageUiState
    public fun messageShown(messageId: Int, userMessageResult: UserMessageResult)
    public suspend fun showMessage(
        message: String,
        actionLabel: String? = null,
        duration: SnackbarDuration? = null,
    ): UserMessageResult
}

public class DefaultUserMessageStateHolder : UserMessageStateHolder {
    private var _messageUiState by mutableStateOf(MessageUiState())
    override val messageUiState: MessageUiState get() = _messageUiState
    override fun messageShown(messageId: Int, userMessageResult: UserMessageResult) {
        val messages = _messageUiState.userMessages.toMutableList()
        messages.indexOfFirst { it.id == messageId }.let { userMessageIndex ->
            if (userMessageIndex == -1) return@let
            messages.set(
                userMessageIndex,
                messages[userMessageIndex].copy(userMessageResult = userMessageResult),
            )
        }
        _messageUiState = _messageUiState.copy(userMessages = messages)
    }

    override suspend fun showMessage(
        message: String,
        actionLabel: String?,
        duration: SnackbarDuration?,
    ): UserMessageResult {
        val messages = _messageUiState.userMessages.toMutableList()
        val newMessage = UserMessage(message, actionLabel = actionLabel, duration = duration)
        messages.add(newMessage)
        _messageUiState = _messageUiState.copy(userMessages = messages)
        val messageResult = snapshotFlow {
            _messageUiState
        }.filter { messageState ->
            messageState.userMessages.find { it.id == newMessage.id }?.let { userMessage ->
                val messageResult = userMessage.userMessageResult
                messageResult != null
            } ?: false
        }
            .map { messageState ->
                val userMessage = checkNotNull(
                    messageState.userMessages.find { it.id == newMessage.id },
                )
                checkNotNull(userMessage.userMessageResult)
            }
            .first()
        val newMessages = _messageUiState.userMessages.toMutableList()
        newMessages.find { it.id == newMessage.id }?.let { userMessage ->
            newMessages.remove(userMessage)
        }
        _messageUiState = _messageUiState.copy(userMessages = newMessages)
        return messageResult
    }
}
