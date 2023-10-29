package club.nito.feature.top

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val topNavigationRoute = "top_route"

fun NavController.navigateToTop(navOptions: NavOptions? = null) {
    this.navigate(topNavigationRoute, navOptions)
}

fun NavGraphBuilder.topScreen() {
    composable(
        route = topNavigationRoute,
    ) {
        TopRoute()
    }
}
