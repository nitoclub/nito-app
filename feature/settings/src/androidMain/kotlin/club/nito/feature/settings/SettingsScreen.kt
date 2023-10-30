package club.nito.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import club.nito.core.designsystem.component.CenterAlignedTopAppBar
import club.nito.core.designsystem.component.Scaffold
import club.nito.core.designsystem.component.Text
import club.nito.core.ui.message.SnackbarMessageEffect

@Composable
fun SettingsRoute(
    viewModel: SettingsViewModel = hiltViewModel(),
    onSignedOut: () -> Unit = {},
) {
    viewModel.event.collectAsState(initial = null).value?.let {
        LaunchedEffect(it.hashCode()) {
            when (it) {
                SettingsEvent.SignedOut -> onSignedOut()
            }
            viewModel.consume(it)
        }
    }

    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    SnackbarMessageEffect(
        snackbarHostState = snackbarHostState,
        userMessageStateHolder = viewModel.userMessageStateHolder,
    )

    SettingsScreen(
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        dispatch = viewModel::dispatch,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsScreen(
    uiState: SettingsScreenUiState,
    snackbarHostState: SnackbarHostState,
    dispatch: (SettingsIntent) -> Unit = {},
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "設定",
                    )
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { dispatch(SettingsIntent.ClickSignOut) },
                    enabled = uiState.isSignOutButtonEnabled,
                ) {
                    Text(
                        text = "Sign Out",
                    )
                }
            }
        },
    )
}
