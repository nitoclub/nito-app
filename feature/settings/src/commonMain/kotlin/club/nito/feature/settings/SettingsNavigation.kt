package club.nito.feature.settings

import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder

const val settingsNavigationRoute = "settings_route"

fun Navigator.navigateToSettings(navOptions: NavOptions? = null) {
    this.navigate(settingsNavigationRoute, navOptions)
}

fun RouteBuilder.settingsScreen(
    onSignedOut: () -> Unit = {},
) {
    scene(
        route = settingsNavigationRoute,
    ) {
        SettingsRoute(onSignedOut = onSignedOut)
    }
}
