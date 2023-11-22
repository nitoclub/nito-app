package club.nito.feature.settings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

fun NavController.navigateToSettings(navOptions: NavOptions? = null) {
    this.navigate(settingsNavigationRoute, navOptions)
}

fun NavGraphBuilder.settingsScreen(
    onSignedOut: () -> Unit = {},
) {
    composable(
        route = settingsNavigationRoute,
    ) {
        SettingsRoute(onSignedOut = onSignedOut)
    }
}
