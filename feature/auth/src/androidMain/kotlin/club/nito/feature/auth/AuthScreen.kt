package club.nito.feature.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import club.nito.core.designsystem.component.CenterAlignedTopAppBar
import club.nito.core.designsystem.component.Scaffold
import club.nito.core.designsystem.component.Text

@Composable
fun AuthRoute(
    onSignInClick: () -> Unit = {},
) {
    AuthScreen(onSignInClick = onSignInClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AuthScreen(
    onSignInClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Auth",
                    )
                },
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .padding(16.dp),
            ) {
                Text(
                    text = "Auth",
                )
                Button(onClick = onSignInClick) {
                    Text(
                        text = "Sign In",
                    )
                }
            }
        },
    )
}
