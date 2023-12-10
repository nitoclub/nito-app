package club.nito.feature.auth.login

import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder

public const val loginNavigationRoute: String = "login_route"

public fun Navigator.navigateToLogin(navOptions: NavOptions? = null) {
    this.navigate(loginNavigationRoute, navOptions)
}

public fun RouteBuilder.loginScreen(
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
