package club.nito.core.database.di

import club.nito.core.database.DriverFactory
import org.koin.core.scope.Scope

internal actual fun Scope.createDriverFactory(): DriverFactory = DriverFactory(
    context = get(),
)
