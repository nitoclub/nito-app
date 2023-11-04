package club.nito.core.model.participation

/**
 * 参加表明
 * @param scheduleId スケジュールID
 * @param memberId メンバーID
 * @param comment コメント
 */
data class ParticipantDeclaration(
    val scheduleId: String,
    val memberId: String,
    val comment: String,
)
