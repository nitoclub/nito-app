package club.nito.app.shared.di

import club.nito.core.common.NitoDateFormatter
import kotlinx.datetime.Instant
import kotlinx.datetime.toJavaInstant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

actual fun createNitoDateTimeFormatter(): NitoDateFormatter {
    val formatter = DateTimeFormatter
        .ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT)
        .withLocale(Locale.getDefault())
        .withZone(ZoneId.systemDefault())

    return object : NitoDateFormatter {
        override fun formatDateTime(instant: Instant): String = formatter.format(instant.toJavaInstant())
    }
}
