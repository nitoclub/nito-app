package club.nito.core.designsystem.component

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@ExperimentalMaterial3Api
@Composable
fun TopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior? = null,
) = androidx.compose.material3.TopAppBar(
    title = title,
    modifier = modifier,
    navigationIcon = navigationIcon,
    actions = actions,
    windowInsets = windowInsets,
    colors = colors,
    scrollBehavior = scrollBehavior,
)

@ExperimentalMaterial3Api
@Composable
fun CenterAlignedTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
    scrollBehavior: TopAppBarScrollBehavior? = null,
) = androidx.compose.material3.CenterAlignedTopAppBar(
    title = title,
    modifier = modifier,
    navigationIcon = navigationIcon,
    actions = actions,
    windowInsets = windowInsets,
    colors = colors,
    scrollBehavior = scrollBehavior,
)
