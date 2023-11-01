package club.nito.core.common

import kotlinx.datetime.Instant
import kotlinx.datetime.toJavaInstant
import java.time.format.DateTimeFormatter

sealed interface NitoDateTimeFormatter {
    /**
     * 日時をフォーマットする
     */
    fun formatDateTimeString(instant: Instant): String
}

class DefaultNitoDateTimeFormatter(
    private val dateTimeFormatter: DateTimeFormatter,
) : NitoDateTimeFormatter {
    override fun formatDateTimeString(instant: Instant): String {
        val string = dateTimeFormatter.format(instant)
        return string
    }

    private fun DateTimeFormatter.format(instant: Instant): String = format(instant.toJavaInstant())
}
