package club.nito.core.domain

import club.nito.core.data.ParticipantRepository
import club.nito.core.data.PlaceRepository
import club.nito.core.data.ScheduleRepository
import club.nito.core.data.UserRepository
import club.nito.core.domain.model.ParticipantSchedule
import club.nito.core.model.FetchSingleContentResult
import club.nito.core.model.Order
import club.nito.core.model.UserProfile
import club.nito.core.model.place.Place
import club.nito.core.model.schedule.Schedule
import club.nito.core.model.toNitoError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock

/**
 * 直近のスケジュールを取得するユースケース
 */
public sealed interface GetRecentScheduleUseCase {
    public operator fun invoke(): Flow<FetchSingleContentResult<ParticipantSchedule>>
}

public class GetRecentScheduleExecutor(
    private val scheduleRepository: ScheduleRepository,
    private val participantRepository: ParticipantRepository,
    private val placeRepository: PlaceRepository,
    private val userRepository: UserRepository,
) : GetRecentScheduleUseCase {
    override fun invoke(): Flow<FetchSingleContentResult<ParticipantSchedule>> = flow {
        val data = try {
            scheduleRepository.getScheduleList(
                limit = 1,
                order = Order.ASCENDING,
                after = Clock.System.now(),
            )
        } catch (e: Throwable) {
            emit(FetchSingleContentResult.Failure(error = e.toNitoError()))
            return@flow
        }

        if (data.isEmpty()) {
            emit(FetchSingleContentResult.NoContent)
            return@flow
        }

        val schedule = data.first()

        val participants = participantRepository.getParticipants(scheduleId = schedule.id)
        val userProfiles = userRepository.getProfiles(userIds = participants.map { it.userId })
        val places = placeRepository.fetchPlaceList(schedule.venueId, schedule.meetId)

        val participantSchedule = transformToParticipantSchedule(
            schedule = schedule,
            userProfiles = userProfiles,
            places = places,
        )

        emit(FetchSingleContentResult.Success(participantSchedule))
    }

    private fun transformToParticipantSchedule(
        schedule: Schedule,
        userProfiles: List<UserProfile>,
        places: List<Place>,
    ): ParticipantSchedule {
        return ParticipantSchedule(
            id = schedule.id,
            scheduledAt = schedule.scheduledAt,
            metAt = schedule.metAt,
            venue = places.first { it.id == schedule.venueId },
            meet = places.first { it.id == schedule.meetId },
            description = schedule.description,
            participants = userProfiles,
        )
    }
}
