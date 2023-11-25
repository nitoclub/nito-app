package club.nito.core.common

import kotlinx.datetime.Instant

public interface NitoDateFormatter {
    /**
     * 日時をフォーマットする
     */
    public fun formatDateTime(instant: Instant): String
}
