package club.nito.feature.auth

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val signInNavigationRoute = "signin_route"

fun NavController.navigateToSignIn(navOptions: NavOptions? = null) {
    this.navigate(signInNavigationRoute, navOptions)
}

fun NavGraphBuilder.signInScreen(
    onSignedIn: () -> Unit = {},
    onRegisterClick: () -> Unit = {},
) {
    composable(
        route = signInNavigationRoute,
    ) {
        SignInRoute(
            onSignedIn = onSignedIn,
            onRegisterClick = onRegisterClick,
        )
    }
}
