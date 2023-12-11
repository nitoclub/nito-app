package club.nito.core.database.adapter

import app.cash.sqldelight.ColumnAdapter
import club.nito.core.model.participant.ParticipantStatus

internal data object ParticipantStatusAdapter : ColumnAdapter<ParticipantStatus, String> {
    override fun decode(databaseValue: String): ParticipantStatus = ParticipantStatus.valueOf(databaseValue)

    override fun encode(value: ParticipantStatus): String = value.name
}
