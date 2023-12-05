package club.nito.feature.schedule.list

import club.nito.core.model.schedule.ScheduleId
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder

public const val scheduleNavigationRoute: String = "schedule_list"

public fun Navigator.navigateToScheduleList(navOptions: NavOptions? = null) {
    this.navigate(scheduleNavigationRoute, navOptions)
}

public fun RouteBuilder.scheduleListScreen(
    onScheduleItemClick: (ScheduleId) -> Unit = {},
) {
    scene(
        route = scheduleNavigationRoute,
    ) {
        ScheduleListRoute(
            onScheduleItemClick = onScheduleItemClick,
        )
    }
}
