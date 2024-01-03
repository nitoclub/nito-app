package club.nito.feature.schedule.detail

import club.nito.core.model.participant.ParticipantStatus
import club.nito.core.model.participant.ParticipantUser

public sealed class ScheduleDetailIntent {
    public data class ClickParticipantUser(val user: ParticipantUser) : ScheduleDetailIntent()

    public sealed class ClickParticipantStatusChip : ScheduleDetailIntent() {
        public abstract val status: ParticipantStatus

        public data object Participate : ClickParticipantStatusChip() {
            override val status: ParticipantStatus = ParticipantStatus.ATTENDANCE
        }

        public data object Absent : ClickParticipantStatusChip() {
            override val status: ParticipantStatus = ParticipantStatus.ABSENCE
        }

        public data object Hold : ClickParticipantStatusChip() {
            override val status: ParticipantStatus = ParticipantStatus.PENDING
        }
    }
}
