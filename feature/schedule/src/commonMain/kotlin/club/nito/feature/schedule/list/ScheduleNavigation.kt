package club.nito.feature.schedule.list

import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder

public const val scheduleNavigationRoute: String = "schedule_route"

public fun Navigator.navigateToSchedule(navOptions: NavOptions? = null) {
    this.navigate(scheduleNavigationRoute, navOptions)
}

public fun RouteBuilder.scheduleScreen() {
    scene(
        route = scheduleNavigationRoute,
    ) {
        ScheduleRoute()
    }
}
