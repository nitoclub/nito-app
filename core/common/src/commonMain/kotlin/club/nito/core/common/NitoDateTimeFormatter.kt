package club.nito.core.common

import kotlinx.datetime.Instant

interface NitoDateTimeFormatter {
    /**
     * 日時をフォーマットする
     */
    fun formatDateTimeString(instant: Instant): String
}
