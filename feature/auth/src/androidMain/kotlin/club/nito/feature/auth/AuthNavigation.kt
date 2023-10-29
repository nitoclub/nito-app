package club.nito.feature.auth

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val authNavigationRoute = "auth_route"

fun NavController.navigateToAuth(navOptions: NavOptions? = null) {
    this.navigate(authNavigationRoute, navOptions)
}

fun NavGraphBuilder.authScreen(
    onSignInClick: () -> Unit = {},
) {
    composable(
        route = authNavigationRoute,
    ) {
        AuthRoute(onSignInClick = onSignInClick)
    }
}
