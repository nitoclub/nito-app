package club.nito.feature.top

import androidx.compose.ui.window.ComposeUIViewController
import club.nito.core.designsystem.theme.NitoTheme
import platform.UIKit.UIViewController

@Suppress("FunctionName")
public fun TopRouteViewController(
    stateMachine: TopScreenStateMachine,
    onScheduleListClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
): UIViewController = ComposeUIViewController {
    NitoTheme {
        TopRoute(
            stateMachine = stateMachine,
            onScheduleListClick = onScheduleListClick,
            onSettingsClick = onSettingsClick,
        )
    }
}
