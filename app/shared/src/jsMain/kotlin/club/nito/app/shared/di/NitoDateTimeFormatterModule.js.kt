package club.nito.app.shared.di

import club.nito.core.common.NitoDateFormatter
import kotlinx.datetime.Instant

actual fun createNitoDateTimeFormatter(): NitoDateFormatter {
    return object : NitoDateFormatter {
        override fun formatDateTime(instant: Instant): String {
            return instant.toString()
        }
    }
}
