package club.nito.app.shared.di

import club.nito.core.common.NitoDateFormatter
import kotlinx.datetime.Instant
import kotlinx.datetime.toNSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSDateFormatterMediumStyle
import platform.Foundation.NSDateFormatterShortStyle
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale

actual fun createNitoDateTimeFormatter(): NitoDateFormatter {
    val formatter = NSDateFormatter().apply {
        dateStyle = NSDateFormatterMediumStyle
        timeStyle = NSDateFormatterShortStyle
        locale = NSLocale.currentLocale
    }

    return object : NitoDateFormatter {
        override fun formatDateTime(instant: Instant): String {
            return formatter.stringFromDate(date = instant.toNSDate())
        }
    }
}
