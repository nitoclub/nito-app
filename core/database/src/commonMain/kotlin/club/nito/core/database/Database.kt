package club.nito.core.database

import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.db.SqlDriver

internal expect class DriverFactory {
    fun createDriver(): SqlDriver
}

internal const val DATABASE_NAME = "nito.db"

internal fun createDatabase(driverFactory: DriverFactory): Database {
    val driver = driverFactory.createDriver()
    val database = Database(
        driver = driver,
        participantsAdapter = Participants.Adapter(
            statusAdapter = EnumColumnAdapter(),
        ),
    )

    // Do more work with the database (see below).
    return database
}
