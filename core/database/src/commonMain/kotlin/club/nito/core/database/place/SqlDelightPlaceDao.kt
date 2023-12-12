package club.nito.core.database.place

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import club.nito.core.common.NitoCoroutineDispatchers
import club.nito.core.database.Database
import club.nito.core.database.Places
import club.nito.core.database.place.model.toModel
import club.nito.core.model.place.Place
import club.nito.core.model.schedule.ScheduleId
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class SqlDelightPlaceDao(
    private val database: Database,
    private val dispatchers: NitoCoroutineDispatchers,
) : PlaceDao {
    override val placesStream: Flow<List<Place>>
        get() = database.placesQueries.places()
            .asFlow()
            .mapToList(dispatchers.io)
            .map {
                it.map(Places::toModel)
            }

    override fun placeStream(placeId: ScheduleId): Flow<Place?> {
        return database.placesQueries.placeById(placeId)
            .asFlow()
            .mapToOneOrNull(dispatchers.io)
            .map {
                it?.toModel()
            }
    }

    override fun placesStream(placeIds: List<String>): Flow<List<Place>> {
        return database.placesQueries.placesByIds(placeIds)
            .asFlow()
            .mapToList(dispatchers.io)
            .map {
                it.map(Places::toModel)
            }
    }

    override fun upsert(entities: List<Place>) {
        database.transaction {
            for (entity in entities) {
                upsertUserProfile(entity)
            }
        }
    }

    override fun upsert(entity: Place) {
        database.transaction {
            upsertUserProfile(entity)
        }
    }

    private fun upsertUserProfile(entity: Place) {
        database.placesQueries.upsert(
            id = entity.id,
            name = entity.name,
            url = entity.url,
            description = entity.description,
            map_url = entity.mapUrl,
            image_url = entity.imageUrl,
        )
    }
}
