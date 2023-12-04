package club.nito.feature.schedule.list

import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder

public const val scheduleNavigationRoute: String = "schedule_list"

public fun Navigator.navigateToScheduleList(navOptions: NavOptions? = null) {
    this.navigate(scheduleNavigationRoute, navOptions)
}

public fun RouteBuilder.scheduleListScreen() {
    scene(
        route = scheduleNavigationRoute,
    ) {
        ScheduleListRoute()
    }
}
