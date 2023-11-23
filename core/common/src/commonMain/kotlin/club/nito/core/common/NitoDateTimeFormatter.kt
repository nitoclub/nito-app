package club.nito.core.common

import kotlinx.datetime.Instant

public interface NitoDateTimeFormatter {
    /**
     * 日時をフォーマットする
     */
    public fun formatDateTimeString(instant: Instant): String
}
