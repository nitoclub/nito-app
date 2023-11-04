package club.nito.core.model.participant

/**
 * 参加情報
 * @param scheduleId スケジュールID
 * @param userId 参加ユーザーID
 * @param comment コメント
 */
data class Participant(
    val scheduleId: String,
    val userId: String,
    val comment: String,
)
