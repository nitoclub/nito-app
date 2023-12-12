package club.nito.core.database.di

import club.nito.core.database.DriverFactory
import club.nito.core.database.createDatabase
import club.nito.core.database.participant.ParticipantDao
import club.nito.core.database.participant.SqlDelightParticipantDao
import club.nito.core.database.place.PlaceDao
import club.nito.core.database.place.SqlDelightPlaceDao
import club.nito.core.database.profile.ProfileDao
import club.nito.core.database.profile.SqlDelightProfileDao
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
    singleOf(::SqlDelightPlaceDao) bind PlaceDao::class
    singleOf(::SqlDelightProfileDao) bind ProfileDao::class
}

internal expect fun Scope.createDriverFactory(): DriverFactory
