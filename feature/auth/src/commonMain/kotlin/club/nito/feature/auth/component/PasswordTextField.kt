package club.nito.feature.auth.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import club.nito.core.designsystem.component.Text
import club.nito.core.designsystem.icon.NitoIcons

@Composable
internal fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibleChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
) {
    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        modifier = modifier,
        label = { Text("Password") },
        singleLine = true,
        placeholder = { Text("Password") },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = keyboardOptions,
        trailingIcon = {
            val image = if (passwordVisible) NitoIcons.Visibility else NitoIcons.VisibilityOff
            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { onPasswordVisibleChange(passwordVisible.not()) }) {
                Icon(imageVector = image, description)
            }
        },
    )
}
