package club.nito.core.domain

import club.nito.core.data.ParticipantRepository
import club.nito.core.data.ScheduleRepository
import club.nito.core.data.UserRepository
import club.nito.core.domain.model.ParticipantSchedule
import club.nito.core.model.FetchMultipleContentResult
import club.nito.core.model.Schedule
import club.nito.core.model.UserProfile
import club.nito.core.model.participant.Participant
import club.nito.core.model.toNitoError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * スケジュール一覧を取得するユースケース
 */
sealed interface GetParticipantScheduleListUseCase {
    operator fun invoke(): Flow<FetchMultipleContentResult<ParticipantSchedule>>
}

class GetParticipantScheduleListExecutor(
    private val scheduleRepository: ScheduleRepository,
    private val participantRepository: ParticipantRepository,
    private val userRepository: UserRepository,
) : GetParticipantScheduleListUseCase {
    override fun invoke(): Flow<FetchMultipleContentResult<ParticipantSchedule>> = flow {
        val schedules = try {
            scheduleRepository.getScheduleList(limit = 10)
        } catch (e: Throwable) {
            emit(FetchMultipleContentResult.Failure(error = e.toNitoError()))
            return@flow
        }

        if (schedules.isEmpty()) {
            emit(FetchMultipleContentResult.NoContent)
            return@flow
        }

        val participants = participantRepository.getParticipants(scheduleIds = schedules.map { it.id })
        val profiles = userRepository.getProfiles(userIds = participants.distinctBy { it.userId }.map { it.userId })

        val participantScheduleList = transformToParticipantScheduleList(
            schedules = schedules,
            participants = participants,
            userProfiles = profiles,
        )

        emit(FetchMultipleContentResult.Success(participantScheduleList))
    }

    private fun transformToParticipantScheduleList(
        schedules: List<Schedule>,
        participants: List<Participant>,
        userProfiles: List<UserProfile>,
    ): List<ParticipantSchedule> = schedules.map { schedule ->
        val scheduleParticipants = participants.filter { it.scheduleId == schedule.id }
        val scheduleParticipantProfiles = userProfiles.filter { profile ->
            scheduleParticipants.any { it.userId == profile.id }
        }

        ParticipantSchedule(
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
