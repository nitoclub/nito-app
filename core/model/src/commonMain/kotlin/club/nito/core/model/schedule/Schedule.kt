package club.nito.core.model.schedule

import kotlinx.datetime.Instant

/**
 * スケジュール
 * @param id ID
 * @param scheduledAt 予定日時
 * @param metAt 集合日時
 * @param venueId 開催場所ID
 * @param meetId 集合場所ID
 * @param description 説明文
 */
public data class Schedule(
    val id: ScheduleId,
    val scheduledAt: Instant,
    val metAt: Instant,
    val venueId: String,
    val meetId: String,
    val description: String,
)
