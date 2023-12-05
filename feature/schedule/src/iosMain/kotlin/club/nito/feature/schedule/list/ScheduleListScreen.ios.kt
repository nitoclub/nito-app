package club.nito.feature.schedule.list

import androidx.compose.ui.window.ComposeUIViewController
import club.nito.core.designsystem.theme.NitoTheme
import club.nito.core.model.schedule.ScheduleId
import platform.UIKit.UIViewController

@Suppress("FunctionName")
public fun ScheduleListRouteViewController(
    stateMachine: ScheduleListStateMachine,
    onScheduleItemClick: (ScheduleId) -> Unit = {},
): UIViewController = ComposeUIViewController {
    NitoTheme {
        ScheduleListRoute(
            stateMachine = stateMachine,
            onScheduleItemClick = onScheduleItemClick,
            hideTopAppBar = true,
        )
    }
}
