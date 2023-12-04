package club.nito.core.domain

import club.nito.core.data.ParticipantRepository
import club.nito.core.data.ScheduleRepository
import club.nito.core.data.UserRepository
import club.nito.core.domain.extension.toUserIdList
import club.nito.core.domain.model.ParticipantSchedule
import club.nito.core.model.FetchSingleContentResult
import club.nito.core.model.UserProfile
import club.nito.core.model.participant.Participant
import club.nito.core.model.schedule.Schedule
import club.nito.core.model.schedule.ScheduleId
import club.nito.core.model.toNitoError

/**
 * ID を元にスケジュールを取得するユースケース
 */
public sealed interface FetchParticipantScheduleByIdUseCase {
    public suspend operator fun invoke(id: ScheduleId): FetchSingleContentResult<ParticipantSchedule>
}

public class FetchParticipantScheduleByIdExecutor(
    private val scheduleRepository: ScheduleRepository,
    private val participantRepository: ParticipantRepository,
    private val userRepository: UserRepository,
) : FetchParticipantScheduleByIdUseCase {
    override suspend fun invoke(id: ScheduleId): FetchSingleContentResult<ParticipantSchedule> {
        val schedule = try {
            scheduleRepository.fetchSchedule(id = id)
        } catch (e: Throwable) {
            return FetchSingleContentResult.Failure(error = e.toNitoError())
        }

        val participants = participantRepository.getParticipants(id)
        val profiles = userRepository.getProfiles(userIds = participants.toUserIdList())
        val participantSchedule = transformToParticipantSchedule(
            schedule = schedule,
            participants = participants,
            userProfiles = profiles,
        )

        return FetchSingleContentResult.Success(participantSchedule)
    }

    private fun transformToParticipantSchedule(
        schedule: Schedule,
        participants: List<Participant>,
        userProfiles: List<UserProfile>,
    ): ParticipantSchedule {
        val scheduleParticipants = participants.filter { it.scheduleId == schedule.id }
        val scheduleParticipantProfiles = userProfiles.filter { profile ->
            scheduleParticipants.any { it.userId == profile.id }
        }

        return ParticipantSchedule(
            id = schedule.id,
            scheduledAt = schedule.scheduledAt,
            metAt = schedule.metAt,
            venueId = schedule.venueId,
            meetId = schedule.meetId,
            description = schedule.description,
            participants = scheduleParticipantProfiles,
        )
    }
}
