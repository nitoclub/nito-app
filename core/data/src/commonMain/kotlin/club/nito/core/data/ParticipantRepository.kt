package club.nito.core.data

import club.nito.core.model.participant.Participant
import club.nito.core.model.participant.ParticipantDeclaration

/**
 * 参加情報を扱うリポジトリ
 */
sealed interface ParticipantRepository {
    /**
     * 該当の予定の参加情報を取得する
     *
     * @param scheduleId 参加情報を取得するスケジュールID
     */
    suspend fun getParticipants(scheduleId: String): List<Participant>

    /**
     * 該当のスケジュールに参加する
     *
     * @param declaration 参加表明データ
     */
    suspend fun participate(declaration: ParticipantDeclaration): Long
}
