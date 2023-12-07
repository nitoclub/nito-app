package club.nito.core.model.participant

import club.nito.core.model.schedule.ScheduleId

/**
 * 参加表明
 * @param scheduleId スケジュールID
 * @param userId メンバーID
 * @param status 参加状況
 */
public data class ParticipantDeclaration(
    val scheduleId: ScheduleId,
    val userId: String,
    val status: ParticipantStatus,
)
