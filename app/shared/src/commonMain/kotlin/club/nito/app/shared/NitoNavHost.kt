package club.nito.app.shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import club.nito.core.model.AuthStatus
import club.nito.feature.auth.loginNavigationRoute
import club.nito.feature.auth.loginScreen
import club.nito.feature.auth.navigateToLogin
import club.nito.feature.schedule.list.navigateToScheduleList
import club.nito.feature.schedule.list.scheduleListScreen
import club.nito.feature.settings.navigateToSettings
import club.nito.feature.settings.settingsScreen
import club.nito.feature.top.navigateToTop
import club.nito.feature.top.topNavigationRoute
import club.nito.feature.top.topScreen
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.PopUpTo
import moe.tlaster.precompose.navigation.RouteBuilder
import moe.tlaster.precompose.navigation.rememberNavigator

const val rootNavigationRoute = "root_route"

@Composable
fun NitoNavHost(
    authStatus: AuthStatus,
    modifier: Modifier = Modifier,
    navigator: Navigator = rememberNavigator(),
    startDestination: String = rootNavigationRoute,
) {
    NavHost(
        navigator = navigator,
        initialRoute = startDestination,
        modifier = modifier,
    ) {
        root(
            authStatus = authStatus,
            navigator = navigator,
        )

        topScreen(
            onScheduleListClick = navigator::navigateToScheduleList,
            onSettingsClick = navigator::navigateToSettings,
        )
        loginScreen(
            onLoggedIn = {
                navigator.navigateToTop(
                    navOptions = NavOptions(
                        popUpTo = PopUpTo(
                            route = loginNavigationRoute,
                            inclusive = true,
                        ),
                    ),
                )
            },
        )
        scheduleListScreen()
        settingsScreen(
            onSignedOut = {
                navigator.navigateToLogin(
                    navOptions = NavOptions(
                        popUpTo = PopUpTo(
                            route = topNavigationRoute,
                            inclusive = true,
                        ),
                    ),
                )
            },
        )
    }
}

private fun RouteBuilder.root(
    authStatus: AuthStatus,
    navigator: Navigator,
) = scene(rootNavigationRoute) {
    LaunchedEffect(authStatus) {
        when (authStatus) {
            AuthStatus.NotAuthenticated -> navigator.navigateToLogin(
                navOptions = NavOptions(
                    popUpTo = PopUpTo(
                        route = rootNavigationRoute,
                        inclusive = true,
                    ),
                ),
            )

            is AuthStatus.Authenticated -> navigator.navigateToTop(
                navOptions = NavOptions(
                    popUpTo = PopUpTo(
                        route = rootNavigationRoute,
                        inclusive = true,
                    ),
                ),
            )

            else -> {}
        }
    }
}
