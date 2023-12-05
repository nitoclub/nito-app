package club.nito.feature.schedule.detail

import androidx.compose.ui.window.ComposeUIViewController
import club.nito.core.designsystem.theme.NitoTheme
import club.nito.core.model.schedule.ScheduleId
import platform.UIKit.UIViewController

@Suppress("FunctionName")
public fun ScheduleDetailRouteViewController(
    id: ScheduleId,
    stateMachine: ScheduleDetailStateMachine,
): UIViewController = ComposeUIViewController {
    NitoTheme {
        ScheduleDetailRoute(
            id = id,
            stateMachine = stateMachine,
        )
    }
}
