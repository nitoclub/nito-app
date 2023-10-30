package club.nito.core.ui.message

import androidx.compose.material3.SnackbarDuration
import club.nito.core.common.randomUUIDHash

data class UserMessage(
    val message: String,
    val actionLabel: String? = null,
    val duration: SnackbarDuration? = null,
    val id: Int = randomUUIDHash(),
    val userMessageResult: UserMessageResult? = null,
)
