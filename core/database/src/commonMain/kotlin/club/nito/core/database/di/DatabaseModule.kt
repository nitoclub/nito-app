package club.nito.core.database.di

import club.nito.core.database.DriverFactory
import club.nito.core.database.createDatabase
import club.nito.core.database.participant.ParticipantDao
import club.nito.core.database.participant.SqlDelightParticipantDao
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.scope.Scope
import org.koin.dsl.bind
import org.koin.dsl.module

public val databaseModule: Module = module {
    single {
        createDatabase(
            driverFactory = createDriverFactory(),
        )
    }

    singleOf(::SqlDelightParticipantDao) bind ParticipantDao::class
}

internal expect fun Scope.createDriverFactory(): DriverFactory
