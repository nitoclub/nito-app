package club.nito.feature.schedule.detail

import androidx.compose.ui.window.ComposeUIViewController
import club.nito.core.designsystem.theme.NitoTheme
import platform.UIKit.UIViewController

@Suppress("FunctionName")
public fun ScheduleDetailRouteViewController(
    stateMachine: ScheduleDetailStateMachine,
): UIViewController = ComposeUIViewController {
    NitoTheme {
        ScheduleDetailRoute(
            stateMachine = stateMachine,
        )
    }
}
