package club.nito.core.database.di

import club.nito.core.database.DriverFactory
import club.nito.core.database.createDatabase
import org.koin.core.module.Module
import org.koin.core.scope.Scope
import org.koin.dsl.module

public val databaseModule: Module = module {
    single {
        createDatabase(
            driverFactory = createDriverFactory(),
        )
    }
}

internal expect fun Scope.createDriverFactory(): DriverFactory
