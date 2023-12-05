package club.nito.feature.top

import androidx.compose.ui.window.ComposeUIViewController
import club.nito.core.designsystem.theme.NitoTheme
import club.nito.core.model.schedule.ScheduleId
import platform.UIKit.UIViewController

@Suppress("FunctionName")
public fun TopRouteViewController(
    stateMachine: TopScreenStateMachine,
    onRecentScheduleClicked: (ScheduleId) -> Unit = {},
    onScheduleListClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
): UIViewController = ComposeUIViewController {
    NitoTheme {
        TopRoute(
            stateMachine = stateMachine,
            onRecentScheduleClicked = onRecentScheduleClicked,
            onScheduleListClick = onScheduleListClick,
            onSettingsClick = onSettingsClick,
        )
    }
}
