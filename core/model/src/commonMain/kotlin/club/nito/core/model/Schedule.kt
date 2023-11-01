package club.nito.core.model

import kotlinx.datetime.Instant

/**
 * スケジュール
 * @param id ID
 * @param scheduledAt 予定日
 */
data class Schedule(
    val id: String,
    val scheduledAt: Instant,
)
