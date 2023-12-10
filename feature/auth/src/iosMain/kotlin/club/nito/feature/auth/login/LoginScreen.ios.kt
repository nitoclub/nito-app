package club.nito.feature.auth.login

import androidx.compose.ui.window.ComposeUIViewController
import club.nito.core.designsystem.theme.NitoTheme
import platform.UIKit.UIViewController

@Suppress("FunctionName")
public fun LoginRouteViewController(
    viewModel: LoginScreenStateMachine,
    onLoggedIn: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
): UIViewController = ComposeUIViewController {
    NitoTheme {
        LoginRoute(
            viewModel = viewModel,
            onLoggedIn = onLoggedIn,
            onRegisterClick = onRegisterClick,
            hideTopAppBar = true,
        )
    }
}
