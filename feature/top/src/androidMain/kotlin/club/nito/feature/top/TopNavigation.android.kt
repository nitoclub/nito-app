package club.nito.feature.top

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

fun NavController.navigateToTop(navOptions: NavOptions? = null) {
    this.navigate(topNavigationRoute, navOptions)
}

fun NavGraphBuilder.topScreen(
    onScheduleListClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
) {
    composable(
        route = topNavigationRoute,
    ) {
        TopRoute(
            onScheduleListClick = onScheduleListClick,
            onSettingsClick = onSettingsClick,
        )
    }
}
