package club.nito.feature.schedule.list

import androidx.compose.ui.window.ComposeUIViewController
import club.nito.core.designsystem.theme.NitoTheme
import platform.UIKit.UIViewController

@Suppress("FunctionName")
public fun ScheduleListRouteViewController(
    viewModel: ScheduleListStateMachine,
): UIViewController = ComposeUIViewController {
    NitoTheme {
        ScheduleListRoute(
            stateMachine = viewModel,
        )
    }
}
