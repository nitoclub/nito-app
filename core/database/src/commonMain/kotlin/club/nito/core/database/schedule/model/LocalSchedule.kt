package club.nito.core.database.schedule.model

import club.nito.core.model.place.Place
import club.nito.core.model.schedule.Schedule
import club.nito.core.model.schedule.ScheduleWithPlace
import club.nito.core.database.ScheduleWithPlace as LocalScheduleWithPlace
import club.nito.core.database.Schedules as LocalSchedule

internal fun LocalSchedule.toModel(): Schedule = Schedule(
    id = id,
    scheduledAt = scheduled_at,
    metAt = met_at,
    venueId = venue_id,
    meetId = meet_id,
    description = description,
)

internal fun LocalScheduleWithPlace.toModel(): ScheduleWithPlace = ScheduleWithPlace(
    id = id,
    scheduledAt = scheduled_at,
    metAt = met_at,
    venue = Place(
        id = venue_id,
        name = name,
        url = url,
        description = description_,
        mapUrl = map_url,
        imageUrl = image_url,
    ),
    meet = Place(
        id = meet_id,
        name = name_,
        url = url_,
        description = description__,
        mapUrl = map_url_,
        imageUrl = image_url_,
    ),
    description = description,
)
