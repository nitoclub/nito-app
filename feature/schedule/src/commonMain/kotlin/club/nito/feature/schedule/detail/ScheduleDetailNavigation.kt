package club.nito.feature.schedule.detail

import club.nito.core.model.schedule.ScheduleId
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import moe.tlaster.precompose.navigation.path

private const val ROUTE: String = "/schedules"

public fun Navigator.navigateToScheduleDetail(
    id: ScheduleId,
    navOptions: NavOptions? = null,
) {
    this.navigate("$ROUTE/$id", navOptions)
}

public fun RouteBuilder.scheduleDetailScreen() {
    scene(
        route = "$ROUTE/{id}",
    ) { backStackEntry ->
        backStackEntry.path<String>("id")?.let { id ->
            ScheduleDetailRoute(id = id)
        }
    }
}
