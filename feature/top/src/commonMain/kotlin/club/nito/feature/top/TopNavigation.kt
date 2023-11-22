package club.nito.feature.top

import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder

const val topNavigationRoute = "top_route"

fun Navigator.navigateToTop(navOptions: NavOptions? = null) {
    this.navigate(topNavigationRoute, navOptions)
}

fun RouteBuilder.topScreen(
    onScheduleListClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
) {
    scene(
        route = topNavigationRoute,
    ) {
        TopRoute(
            onScheduleListClick = onScheduleListClick,
            onSettingsClick = onSettingsClick,
        )
    }
}
