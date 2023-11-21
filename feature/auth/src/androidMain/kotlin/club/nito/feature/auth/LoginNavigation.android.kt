package club.nito.feature.auth

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

fun NavController.navigateToLogin(navOptions: NavOptions? = null) {
    this.navigate(loginNavigationRoute, navOptions)
}

fun NavGraphBuilder.loginScreen(
    onLoggedIn: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
) {
    composable(
        route = loginNavigationRoute,
    ) {
        LoginRoute(
            onLoggedIn = onLoggedIn,
            onRegisterClick = onRegisterClick,
        )
    }
}
