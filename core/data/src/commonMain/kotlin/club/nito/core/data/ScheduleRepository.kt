package club.nito.core.data

import club.nito.core.model.Order
import club.nito.core.model.schedule.Schedule
import club.nito.core.model.schedule.ScheduleId
import club.nito.core.model.schedule.ScheduleWithPlace
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Instant

/**
 * スケジュールに関するリポジトリ
 */
public sealed interface ScheduleRepository {
    /**
     * スケジュール一覧のストリームを取得する
     */
    public val scheduleListFlow: Flow<List<Schedule>>

    /**
     * スケジュール一覧を取得する
     */
    public suspend fun getScheduleList(
        limit: Int,
        order: Order = Order.DESCENDING,
        after: Instant? = null,
    ): List<Schedule>

    /**
     * 該当スケジュールのストリームを取得する
     */
    public fun scheduleStream(id: ScheduleId): Flow<Schedule?>

    /**
     * 該当スケジュールのストリームを取得する
     */
    public fun scheduleWithPlaceStream(id: ScheduleId): Flow<ScheduleWithPlace?>

    /**
     * スケジュールを取得する
     */
    public suspend fun fetchSchedule(id: ScheduleId): Schedule
}
