package club.nito.core.model.participant

/**
 * 参加状況
 */
public enum class ParticipantStatus {
    /**
     * なし
     */
    NONE,

    /**
     * 未定
     */
    PENDING,

    /**
     * 出席
     */
    ATTENDANCE,

    /**
     * 欠席
     */
    ABSENCE,
}
