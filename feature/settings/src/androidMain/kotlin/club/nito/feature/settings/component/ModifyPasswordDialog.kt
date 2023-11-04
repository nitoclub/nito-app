package club.nito.feature.settings.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import club.nito.core.designsystem.component.Text

@Composable
internal fun ModifyPasswordDialog(
    newPassword: String,
    onNewPasswordChanged: (newPassword: String) -> Unit = {},
    onModifyRequest: () -> Unit = {},
    onDismissRequest: () -> Unit = {},
) {
    AlertDialog(
        title = {
            Text(
                text = "パスワード変更",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
            )
        },
        text = {
            OutlinedTextField(
                value = newPassword,
                onValueChange = onNewPasswordChanged,
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "新しいパスワード") },
                placeholder = { Text(text = "password") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done,
                ),
                singleLine = true,
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
            TextButton(
                onClick = onModifyRequest,
                enabled = newPassword.trim().length > 5,
            ) {
                Text(
                    text = "変更する",
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        },
    )
}
