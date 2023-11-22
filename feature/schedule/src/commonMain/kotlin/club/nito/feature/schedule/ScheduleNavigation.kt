package club.nito.feature.schedule

import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder

const val scheduleNavigationRoute = "schedule_route"

fun Navigator.navigateToSchedule(navOptions: NavOptions? = null) {
    this.navigate(scheduleNavigationRoute, navOptions)
}

fun RouteBuilder.scheduleScreen() {
    scene(
        route = scheduleNavigationRoute,
    ) {
        ScheduleRoute()
    }
}
