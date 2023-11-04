package club.nito.core.domain

import club.nito.core.data.ParticipantRepository
import club.nito.core.data.ScheduleRepository
import club.nito.core.data.UserRepository
import club.nito.core.domain.model.ParticipantSchedule
import club.nito.core.model.FetchSingleContentResult
import club.nito.core.model.Schedule
import club.nito.core.model.UserProfile
import club.nito.core.model.toNitoError
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * 直近のスケジュールを取得するユースケース
 */
sealed interface GetRecentScheduleUseCase {
    operator fun invoke(): Flow<FetchSingleContentResult<ParticipantSchedule>>
}

class GetRecentScheduleExecutor(
    private val scheduleRepository: ScheduleRepository,
    private val participantRepository: ParticipantRepository,
    private val userRepository: UserRepository,
) : GetRecentScheduleUseCase {
    override fun invoke(): Flow<FetchSingleContentResult<ParticipantSchedule>> = flow {
        val data = try {
            scheduleRepository.getScheduleList(limit = 1)
        } catch (e: Throwable) {
            emit(FetchSingleContentResult.Failure(error = e.toNitoError()))
            return@flow
        }

        if (data.isEmpty()) {
            emit(FetchSingleContentResult.NoContent)
            return@flow
        }

        val schedule = data.first()

        val asyncUserProfiles = coroutineScope {
            async {
                val participants = participantRepository.getParticipants(scheduleId = schedule.id)
                userRepository.getProfiles(userIds = participants.map { it.userId })
            }
        }

        val participantSchedule = transformToParticipantSchedule(
            schedule = schedule,
            userProfiles = asyncUserProfiles.await(),
        )

        emit(FetchSingleContentResult.Success(participantSchedule))
    }

    private fun transformToParticipantSchedule(
        schedule: Schedule,
        userProfiles: List<UserProfile>,
    ): ParticipantSchedule {
        return ParticipantSchedule(
            id = schedule.id,
            scheduledAt = schedule.scheduledAt,
            metAt = schedule.metAt,
            venueId = schedule.venueId,
            meetId = schedule.meetId,
            description = schedule.description,
            participants = userProfiles,
        )
    }
}
