package club.nito.feature.settings

import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder

public const val settingsNavigationRoute: String = "settings_route"

public fun Navigator.navigateToSettings(navOptions: NavOptions? = null) {
    this.navigate(settingsNavigationRoute, navOptions)
}

public fun RouteBuilder.settingsScreen(
    onSignedOut: () -> Unit = {},
) {
    scene(
        route = settingsNavigationRoute,
    ) {
        SettingsRoute(onSignedOut = onSignedOut)
    }
}
