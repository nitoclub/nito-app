package club.nito.core.database

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import club.nito.core.model.BuildConfig

internal actual class DriverFactory(
    private val context: Context,
    private val buildConfig: BuildConfig,
) {
    actual fun createDriver(): SqlDriver {
        val schema = Database.Schema

        return AndroidSqliteDriver(
            schema = schema,
            context = context,
            name = databaseName(flavor = buildConfig.flavor),
            callback = object : AndroidSqliteDriver.Callback(schema) {
                override fun onConfigure(db: SupportSQLiteDatabase) {
                    db.enableWriteAheadLogging()
                    db.setForeignKeyConstraintsEnabled(true)
                }
            },
        )
    }
}
