package club.nito.feature.schedule.detail

import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder

public const val scheduleDetailNavigationRoute: String = "schedule_detail"

public fun Navigator.navigateToScheduleDetail(navOptions: NavOptions? = null) {
    this.navigate(scheduleDetailNavigationRoute, navOptions)
}

public fun RouteBuilder.scheduleDetailScreen() {
    scene(
        route = scheduleDetailNavigationRoute,
    ) {
        ScheduleDetailRoute()
    }
}
