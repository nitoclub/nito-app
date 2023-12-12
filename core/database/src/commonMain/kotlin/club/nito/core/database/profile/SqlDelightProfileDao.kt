package club.nito.core.database.profile

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import club.nito.core.common.NitoCoroutineDispatchers
import club.nito.core.database.Database
import club.nito.core.database.Profiles
import club.nito.core.database.profile.model.toModel
import club.nito.core.model.UserProfile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class SqlDelightProfileDao(
    private val database: Database,
    private val dispatchers: NitoCoroutineDispatchers,
) : ProfileDao {
    override fun profileStream(userId: String): Flow<UserProfile?> {
        return database.profilesQueries.profileById(userId)
            .asFlow()
            .mapToOneOrNull(dispatchers.io)
            .map {
                it?.toModel()
            }
    }

    override fun profilesStream(userIds: List<String>): Flow<List<UserProfile>> {
        return database.profilesQueries.profileByIds(userIds)
            .asFlow()
            .mapToList(dispatchers.io)
            .map {
                it.map(Profiles::toModel)
            }
    }

    override fun upsert(entities: List<UserProfile>) {
        database.transaction {
            for (entity in entities) {
                upsertUserProfile(entity)
            }
        }
    }

    override fun upsert(entity: UserProfile) {
        database.transaction {
            upsertUserProfile(entity)
        }
    }

    private fun upsertUserProfile(entity: UserProfile) {
        database.profilesQueries.upsert(
            id = entity.id,
            username = entity.username,
            display_name = entity.displayName,
            avatar_url = entity.avatarUrl,
            website = entity.website,
        )
    }
}
