package club.nito.feature.top

import club.nito.core.model.schedule.ScheduleId
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder

public const val topNavigationRoute: String = "top_route"

public fun Navigator.navigateToTop(navOptions: NavOptions? = null) {
    this.navigate(topNavigationRoute, navOptions)
}

public fun RouteBuilder.topScreen(
    onRecentScheduleClicked: (ScheduleId) -> Unit = {},
    onScheduleListClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
) {
    scene(
        route = topNavigationRoute,
    ) {
        TopRoute(
            onRecentScheduleClicked = onRecentScheduleClicked,
            onScheduleListClick = onScheduleListClick,
            onSettingsClick = onSettingsClick,
        )
    }
}
