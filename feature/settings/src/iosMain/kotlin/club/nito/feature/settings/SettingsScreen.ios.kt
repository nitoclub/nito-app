package club.nito.feature.settings

import androidx.compose.ui.window.ComposeUIViewController
import club.nito.core.designsystem.theme.NitoTheme
import platform.UIKit.UIViewController

@Suppress("FunctionName")
public fun SettingsScreenUIViewController(
    stateMachine: SettingsScreenStateMachine,
    onLoggedOut: () -> Unit = {},
): UIViewController = ComposeUIViewController {
    NitoTheme {
        SettingsRoute(
            stateMachine = stateMachine,
            onSignedOut = onLoggedOut,
            hideTopAppBar = true,
        )
    }
}
