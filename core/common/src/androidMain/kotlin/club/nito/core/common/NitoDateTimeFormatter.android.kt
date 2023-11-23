package club.nito.core.common

import kotlinx.datetime.Instant
import kotlinx.datetime.toJavaInstant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Locale

public class DefaultNitoDateTimeFormatter(
    private val dateTimeFormatter: DateTimeFormatter,
) : NitoDateTimeFormatter {
    override fun formatDateTimeString(instant: Instant): String {
        val string = dateTimeFormatter.format(instant)
        return string
    }

    private fun DateTimeFormatter.format(instant: Instant): String = format(instant.toJavaInstant())
}

public val previewNitoDateTimeFormatter: NitoDateTimeFormatter = DefaultNitoDateTimeFormatter(
    dateTimeFormatter = DateTimeFormatter
        .ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT)
        .withLocale(Locale.getDefault())
        .withZone(ZoneId.systemDefault()),
)
