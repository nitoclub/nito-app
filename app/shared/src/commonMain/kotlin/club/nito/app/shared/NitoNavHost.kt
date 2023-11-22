package club.nito.app.shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import club.nito.core.model.AuthStatus
import club.nito.feature.schedule.navigateToSchedule
import club.nito.feature.schedule.scheduleScreen
import club.nito.feature.top.topNavigationRoute
import club.nito.feature.top.topScreen
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.Navigator
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
        initialRoute = topNavigationRoute,
    ) {
        topScreen(
            onScheduleListClick = navigator::navigateToSchedule,
            onSettingsClick = {},
        )
        scheduleScreen()
    }
}
