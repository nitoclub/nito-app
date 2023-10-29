package club.nito.android

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import club.nito.core.designsystem.theme.NitoTheme
import club.nito.feature.top.topNavigationRoute
import club.nito.feature.top.topScreen

@Composable
fun NitoApp(
    windowSize: WindowSizeClass,
    modifier: Modifier = Modifier,
) {
    NitoTheme {
        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            NitoNavHost(
                windowSize = windowSize,
            )
        }
    }
}

@Composable
private fun NitoNavHost(
    windowSize: WindowSizeClass,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = topNavigationRoute,
) {
    NavHostWithSharedAxisX(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        topScreen()
    }
}
