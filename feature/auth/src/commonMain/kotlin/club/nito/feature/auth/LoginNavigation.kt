package club.nito.feature.auth

import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder

const val loginNavigationRoute = "login_route"

fun Navigator.navigateToLogin(navOptions: NavOptions? = null) {
    this.navigate(loginNavigationRoute, navOptions)
}

fun RouteBuilder.loginScreen(
    onLoggedIn: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
) {
    scene(
        route = loginNavigationRoute,
    ) {
        LoginRoute(
            onLoggedIn = onLoggedIn,
            onRegisterClick = onRegisterClick,
        )
    }
}
