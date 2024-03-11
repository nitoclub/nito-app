package club.nito.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import club.nito.core.model.BuildConfig

internal actual class DriverFactory(
    private val buildConfig: BuildConfig,
) {
    actual fun createDriver(): SqlDriver = NativeSqliteDriver(
        schema = Database.Schema,
        name = databaseName(flavor = buildConfig.flavor),
    )
}
