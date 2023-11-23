package club.nito.feature.top

import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder

public const val topNavigationRoute: String = "top_route"

public fun Navigator.navigateToTop(navOptions: NavOptions? = null) {
    this.navigate(topNavigationRoute, navOptions)
}

public fun RouteBuilder.topScreen(
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
