package club.nito.core.common

import kotlinx.datetime.Instant
import kotlinx.datetime.toJavaInstant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

public class DefaultNitoDateFormatter(
    private val dateTimeFormatter: DateTimeFormatter,
) : NitoDateFormatter {
    override fun formatDateTimeString(instant: Instant): String {
        val string = dateTimeFormatter.format(instant)
        return string
    }

    private fun DateTimeFormatter.format(instant: Instant): String = format(instant.toJavaInstant())
}

public val previewNitoDateFormatter: NitoDateFormatter = DefaultNitoDateFormatter(
    dateTimeFormatter = DateTimeFormatter
        .ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT)
        .withLocale(Locale.getDefault())
        .withZone(ZoneId.systemDefault()),
)
