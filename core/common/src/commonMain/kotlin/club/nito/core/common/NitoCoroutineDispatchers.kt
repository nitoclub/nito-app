package club.nito.core.common

import kotlinx.coroutines.CoroutineDispatcher

public data class NitoCoroutineDispatchers(
    val io: CoroutineDispatcher,
    val databaseWrite: CoroutineDispatcher,
    val databaseRead: CoroutineDispatcher,
    val computation: CoroutineDispatcher,
    val main: CoroutineDispatcher,
)
