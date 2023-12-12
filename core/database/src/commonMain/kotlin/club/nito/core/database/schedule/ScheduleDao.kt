package club.nito.core.database.schedule

import club.nito.core.model.schedule.Schedule
import club.nito.core.model.schedule.ScheduleId
import club.nito.core.model.schedule.ScheduleWithPlace
import kotlinx.coroutines.flow.Flow

public interface ScheduleDao {
    /**
     * スケジュールのストリームを取得する
     */
    public val schedulesStream: Flow<List<Schedule>>

    /**
     * スケジュールのストリームを取得する
     *
     * @param scheduleId 取得するスケジュールの ID
     */
    public fun scheduleStream(scheduleId: ScheduleId): Flow<Schedule?>

    /**
     * 場所情報付きスケジュールのストリームを取得する
     *
     * @param scheduleId 取得するスケジュールの ID
     */
    public fun scheduleWithPlaceStream(scheduleId: ScheduleId): Flow<ScheduleWithPlace?>

    /**
     * スケジュールのストリームを取得する
     *
     * @param scheduleIds 取得するスケジュールの ID 配列
     */
    public fun schedulesStream(scheduleIds: List<ScheduleId>): Flow<List<Schedule>>

    /**
     * スケジュール一覧を登録 / 更新する
     */
    public fun upsert(entities: List<Schedule>)

    /**
     * スケジュールを登録 / 更新する
     */
    public fun upsert(entity: Schedule)
}
