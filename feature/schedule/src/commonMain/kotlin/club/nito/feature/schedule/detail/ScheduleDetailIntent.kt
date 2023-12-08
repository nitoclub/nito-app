package club.nito.feature.schedule.detail

import club.nito.core.domain.model.ParticipantSchedule
import club.nito.core.domain.model.ParticipantUser
import club.nito.core.model.participant.ParticipantStatus

public sealed class ScheduleDetailIntent {
    public data class ClickParticipantUser(val user: ParticipantUser) : ScheduleDetailIntent()

    public sealed class ClickParticipantStatusChip : ScheduleDetailIntent() {
        public abstract val schedule: ParticipantSchedule
        public abstract val status: ParticipantStatus

        public data class Participate(
            override val schedule: ParticipantSchedule,
        ) : ClickParticipantStatusChip() {
            override val status: ParticipantStatus = ParticipantStatus.ATTENDANCE
        }

        public data class Absent(
            override val schedule: ParticipantSchedule,
        ) : ClickParticipantStatusChip() {
            override val status: ParticipantStatus = ParticipantStatus.ABSENCE
        }

        public data class Hold(
            override val schedule: ParticipantSchedule,
        ) : ClickParticipantStatusChip() {
            override val status: ParticipantStatus = ParticipantStatus.PENDING
        }
    }
}
