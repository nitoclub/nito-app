package club.nito.app.shared.di

import club.nito.core.common.NitoDateTimeFormatter
import kotlinx.datetime.Instant

actual fun createNitoDateTimeFormatter(): NitoDateTimeFormatter {
    return object : NitoDateTimeFormatter {
        override fun formatDateTimeString(instant: Instant): String {
            return instant.toString()
        }
    }
}
