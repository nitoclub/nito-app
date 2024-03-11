package club.nito.core.database

import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import club.nito.core.database.adapter.InstantLongColumnAdapter
import club.nito.core.model.Flavor

internal expect class DriverFactory {
    fun createDriver(): SqlDriver
}

internal fun databaseName(flavor: Flavor): String = when (flavor) {
    Flavor.Dev -> "nito-dev.db"
    Flavor.Prod -> "nito.db"
}

internal fun createDatabase(driverFactory: DriverFactory): Database {
    val driver = driverFactory.createDriver()
    val database = Database(
        driver = driver,
        participantsAdapter = Participants.Adapter(
            statusAdapter = EnumColumnAdapter(),
        ),
        schedulesAdapter = Schedules.Adapter(
            scheduled_atAdapter = InstantLongColumnAdapter,
            met_atAdapter = InstantLongColumnAdapter,
        ),
    )

    // Do more work with the database (see below).
    return database
}
