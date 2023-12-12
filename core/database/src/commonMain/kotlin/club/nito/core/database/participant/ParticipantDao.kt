package club.nito.core.database.participant

import club.nito.core.model.participant.Participant
import club.nito.core.model.participant.ParticipantStatus
import club.nito.core.model.participant.ParticipantUser
import kotlinx.coroutines.flow.Flow

public interface ParticipantDao {
    /**
     * 該当の予定の参加情報のストリームを取得する
     *
     * @param scheduleId 参加情報を取得するスケジュールID
     */
    public fun participantUsersStream(scheduleId: String): Flow<List<ParticipantUser>>

    /**
     * 該当の予定の参加情報のストリームを取得する
     *
     * @param scheduleId 参加情報を取得するスケジュールID
     */
    public fun participantStatusStream(scheduleId: String, userId: String): Flow<ParticipantStatus>

    /**
     * 参加者を登録 / 更新する
     */
    public fun upsert(entities: List<Participant>)

    /**
     * 参加者を登録 / 更新する
     */
    public fun upsert(entity: Participant)
}
