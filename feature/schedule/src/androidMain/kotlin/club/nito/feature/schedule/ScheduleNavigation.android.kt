package club.nito.feature.schedule

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

fun NavController.navigateToSchedule(navOptions: NavOptions? = null) {
    this.navigate(scheduleNavigationRoute, navOptions)
}

fun NavGraphBuilder.scheduleScreen() {
    composable(
        route = scheduleNavigationRoute,
    ) {
        ScheduleRoute()
    }
}
